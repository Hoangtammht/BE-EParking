package com.eparking.eparking.controller;

import com.eparking.eparking.dao.CarDetailMapper;
import com.eparking.eparking.dao.ParkingMapper;
import com.eparking.eparking.dao.UserMapper;
import com.eparking.eparking.domain.Parking;
import com.eparking.eparking.domain.Reservation;
import com.eparking.eparking.domain.User;
import com.eparking.eparking.domain.response.*;
import com.eparking.eparking.domain.resquest.RequestReservation;
import com.eparking.eparking.domain.resquest.RequestUpdatestatus;
import com.eparking.eparking.exception.ApiRequestException;
import com.eparking.eparking.service.interf.ReservationService;
import com.eparking.eparking.service.interf.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.sql.Timestamp;
import java.time.Instant;

@CrossOrigin
@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;
    private final UserService userService;
    private final ParkingMapper parkingMapper;
    private final CarDetailMapper carDetailMapper;
    private final UserMapper userMapper;
    @GetMapping("/getListOrder")
    public ResponseEntity<Page<ResponseReservation>> getListParking(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam int statusID) {
        try {
            Page<ResponseReservation> reservations = reservationService.getListOrderByUserAndStatusID(statusID,size,page);
            return ResponseEntity.ok(reservations);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @PostMapping("/createReservation")
    public ResponseEntity<?> createReservation(
            @RequestBody RequestReservation requestReservation,
            HttpServletResponse response,
            HttpServletRequest request){
        try {
            ResponseParking responseParking = parkingMapper.findParkingByParkingID(requestReservation.getParkingID());
            if(responseParking.getPark() == 0){
                return ResponseEntity.ok("This parking lot is full!");
            }
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByPhoneNumber(authentication.getName());
            if(user.getBalance() - requestReservation.getTotalPrice() < 0){
                return ResponseEntity.ok("Not enough money to reserve a place at this parking lot !");
            }
            ResponseReservation createReservations = reservationService.createReservation(requestReservation);
            userMapper.updateWalletForUser(user.getUserID(),user.getBalance() - requestReservation.getTotalPrice());
            User userSup = userMapper.findUserByUserID(responseParking.getUserID());
            userMapper.updateWalletForUser(userSup.getUserID(), userSup.getBalance() + requestReservation.getTotalPrice());
            parkingMapper.updateParkForParking(requestReservation.getParkingID(),responseParking.getPark() - 1);
            return ResponseEntity.ok(createReservations);
        } catch (ApiRequestException e) {
            throw e;
        }
    }
    @GetMapping("/getReservationByID/{reserveID}")
    public ResponseEntity<ResponseGetReservation> getReservationByID(@PathVariable int reserveID){
        try{
            ResponseReservation reservation = reservationService.getResponseReservationByReservationID(reserveID);
            ResponseUser responseUser = userService.getUserProfile();
            ResponseParking responseParking = parkingMapper.findParkingByParkingID(reservation.getParkingID());
            ResponseCarDetail carDetail = carDetailMapper.findCarDetailByCarID(reservation.getCarID());
            ResponseUserRegister responseUserRegister = new ResponseUserRegister(responseUser.getPhoneNumber(),responseUser.getFullName(), responseUser.getIdentifyCard(), responseUser.getRoleName(),responseUser.getBalance());
            ResponseGetReservation responseGetReservation = new ResponseGetReservation(reservation.getReserveID(),responseUserRegister,responseParking,reservation.getAddress(),reservation.getPricing(),reservation.getStatusID(),
                    reservation.getStartDateTime(),reservation.getEndDatetime(),carDetail,reservation.getTotalPrice());
            return ResponseEntity.ok(responseGetReservation);
        }catch (Exception e){
            throw e;
        }
    }
    @PutMapping("/updateStatus")
    public ResponseEntity<ResponseReservation> updateStatus(@RequestBody RequestUpdatestatus requestReservation){
        try{
            ResponseReservation responseReservation = reservationService.updateStatus(requestReservation.getStatusID(), requestReservation.getReserveID());
            Reservation reservation = reservationService.getReservationDetailByReservationID(requestReservation.getReserveID());
            if(responseReservation.getStatusID() == 3){
                ResponseParking responseParking = parkingMapper.findParkingByParkingID(reservation.getParkingID());
                parkingMapper.updateParkForParking(responseParking.getParkingID(),responseParking.getPark() + 1);
            }
            return ResponseEntity.ok(responseReservation);

        }catch (Exception e){
            throw e;
        }
    }
}
