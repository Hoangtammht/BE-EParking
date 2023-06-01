package com.eparking.eparking.controller;

import com.eparking.eparking.domain.response.ResponseCarDetail;
import com.eparking.eparking.domain.response.ResponseCarInParking;
import com.eparking.eparking.domain.resquest.DeleteCar;
import com.eparking.eparking.domain.resquest.RequestCar;
import com.eparking.eparking.exception.ApiRequestException;
import com.eparking.eparking.service.interf.CarDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {

    private final CarDetailService carDetailService;

    @PostMapping("/addCar")
    public ResponseEntity<ResponseCarDetail> addCar(@RequestBody RequestCar resquestCar,
                                                          HttpServletResponse response,
                                                          HttpServletRequest request) {
        try {
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/car/addCar")
                    .toUriString());
            ResponseCarDetail addedCar = carDetailService.addCar(resquestCar.getLicensePlate());
            return ResponseEntity.created(uri).body(addedCar);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @DeleteMapping("/removeCar")
    public ResponseEntity<Map<String, Object>> removeCar(@RequestBody DeleteCar deleteCar,
                                                         HttpServletResponse response,
                                                         HttpServletRequest request) {
        try {
            ResponseCarDetail deletedCar = carDetailService.removeCar(deleteCar.getCarID());
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("message", "Car successfully removed");
            responseMap.put("deletedCar", deletedCar);
            return ResponseEntity.ok(responseMap);
        } catch (ApiRequestException e) {
            throw e;
        }
    }


    @GetMapping("/showCarsInParkingByStatus")
    public ResponseEntity<Page<ResponseCarInParking>> showCarsInParkingByStatus(
            @RequestParam int page, @RequestParam int size, @RequestParam int status,
            HttpServletResponse response,
            HttpServletRequest request) {
        try {
            Page<ResponseCarInParking> carInParkings = carDetailService.findCarsInParkingByStatus(status, page - 1, size);
            return ResponseEntity.ok(carInParkings);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @GetMapping("/getListCarOfUser")
    public ResponseEntity<List<ResponseCarDetail>> getListCarOfUser(HttpServletResponse response,
                                                      HttpServletRequest request){
        try {
            List<ResponseCarDetail> listCar = carDetailService.getListCarOfUser();
            return ResponseEntity.ok(listCar);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

}
