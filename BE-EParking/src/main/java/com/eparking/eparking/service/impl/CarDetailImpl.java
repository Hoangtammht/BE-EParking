package com.eparking.eparking.service.impl;

import com.eparking.eparking.dao.CarDetailMapper;
import com.eparking.eparking.domain.CarDetail;
import com.eparking.eparking.domain.response.ResponseCarDetail;
import com.eparking.eparking.domain.response.ResponseCarInParking;
import com.eparking.eparking.domain.response.ResponseUser;
import com.eparking.eparking.domain.response.ResponseUserRegister;
import com.eparking.eparking.exception.ApiRequestException;
import com.eparking.eparking.service.interf.CarDetailService;
import com.eparking.eparking.service.interf.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarDetailImpl implements CarDetailService {

    private final CarDetailMapper carDetailMapper;
    private final UserService userService;

    @Override
    @Transactional
    public ResponseCarDetail addCar(String licensePlate) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            int userID = userService.findUserByPhoneNumber(authentication.getName()).getUserID();
            CarDetail carDetail = new CarDetail();
            carDetail.setUserID(userID);
            carDetail.setLicensePlate(licensePlate);
            carDetailMapper.addCar(carDetail);
            return carDetailMapper.getNewlyCar();
        } catch (Exception e) {
            // Handle the exception appropriately
            throw new RuntimeException("Failed to add car");
        }
    }


    @Override
    public void removeCar(int carID) {
        try {
            carDetailMapper.removeCar(carID);
        } catch (Exception e) {
            throw new RuntimeException("Failed to remove car");
        }
    }

    @Override
    public Page<ResponseCarInParking> findCarsInParkingByStatus(int status, int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            int offset = page * size;
            List<ResponseCarInParking> carsParking = carDetailMapper.findCarsInParkingByStatus(status, size, offset);
            for (ResponseCarInParking carList: carsParking) {
                ResponseUserRegister customerInfo = userService.findResponseUserRegisterByUserID(carList.getCustomerID());
                carList.setCustomerInfo(customerInfo);
            }
            long totalCount = carDetailMapper.getNumberOfReservationByStatus(status);
            return new PageImpl<>(carsParking, pageable, totalCount);
        } catch (Exception e) {
            throw new ApiRequestException("Failed to find the list cars by revenue status of Supplier");
        }
    }

    @Override
    public List<ResponseCarDetail> findCarDetailByUserID() {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return carDetailMapper.findCarDetailByUserID(userService.findUserByPhoneNumber(authentication.getName()).getUserID());
        }catch (Exception e){
            throw new ApiRequestException("Failed to get list car by this user");
        }
    }


}
