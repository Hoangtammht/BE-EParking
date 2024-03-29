package com.eparking.eparking.controller;

import com.eparking.eparking.domain.CarDetail;
import com.eparking.eparking.domain.response.ResponseCarInParking;
import com.eparking.eparking.domain.resquest.RequestCarsInParking;
import com.eparking.eparking.domain.resquest.ResquestCar;
import com.eparking.eparking.exception.ApiRequestException;
import com.eparking.eparking.service.interf.CarDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {

    private final CarDetailService carDetailService;

    @PostMapping("/addCar")
    public ResponseEntity<List<CarDetail>> addCar(@RequestBody CarDetail carDetail,
                                                  HttpServletResponse response,
                                                  HttpServletRequest request) {
        try {
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/car/addCar")
                    .toUriString());
            List<CarDetail> addedCars = carDetailService.addCar(carDetail);
            return ResponseEntity.created(uri).body(addedCars);
        } catch (Exception e) {
            throw new ApiRequestException("Oops cannot add Information'Car to user");
        }
    }

    @DeleteMapping("/removeCar")
    public void removeCar(@RequestParam int carID,
                          HttpServletResponse response,
                          HttpServletRequest request) {
        try {
            carDetailService.removeCar(carID);
        } catch (Exception e) {
            throw new ApiRequestException("Oops cannot remove car of user");
        }
    }

    @GetMapping("/showCarsInParkingByStatus")
    public ResponseEntity<Page<ResponseCarInParking>> showCarsInParkingByStatus(
            @RequestBody RequestCarsInParking requestCarsInParking,
            HttpServletResponse response,
            HttpServletRequest request) {
        try {
            int status = requestCarsInParking.getStatus();
            int page = requestCarsInParking.getPage();
            int size = requestCarsInParking.getSize();
            Page<ResponseCarInParking> carInParkings = carDetailService.findCarsInParkingByStatus(status, page, size);
            return ResponseEntity.ok(carInParkings);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

}
