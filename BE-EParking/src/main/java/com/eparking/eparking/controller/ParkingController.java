package com.eparking.eparking.controller;

import com.eparking.eparking.domain.*;
import com.eparking.eparking.domain.response.ResponseParking;
import com.eparking.eparking.exception.ApiRequestException;
import com.eparking.eparking.service.interf.ParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/parking")
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;

    @PostMapping("/createParking")
    public ResponseEntity<ResponseParking> createParking(@RequestBody Parking parking,
                                                 HttpServletResponse response,
                                                 HttpServletRequest request) {
        try {
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/parking/createParking")
                    .toUriString());
            ResponseParking newParking = parkingService.createParking(parking);
            return ResponseEntity.created(uri).body(newParking);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @PostMapping("/createSpecialDate")
    public ResponseEntity<SpecialDate> createSpecialDate(@RequestBody SpecialDate specialDate) {
        try {
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/parking/createSpecialDate")
                    .toUriString());
            SpecialDate createdSpecialDate = parkingService.createSpecialDate(specialDate);
            return ResponseEntity.created(uri).body(createdSpecialDate);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @PostMapping("/addDatesForParking")
    public ResponseEntity<List<ParkingDate>> addDatesForParking(@RequestBody List<ParkingDate> parkingDates,
                                                                HttpServletResponse response,
                                                                HttpServletRequest request) {
        try {
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/parking/addDatesForParking")
                    .toUriString());
            List<ParkingDate> dates = parkingService.addDatesForParking(parkingDates);
            return ResponseEntity.created(uri).body(dates);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @PostMapping("/addSpecialDatesForParking")
    public ResponseEntity<List<ParkingSpecialDate>> addSpecialDatesForParking(@RequestBody List<ParkingSpecialDate> parkingSpecialDates,
                                                                       HttpServletResponse response,
                                                                       HttpServletRequest request) {
        try {
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/parking/addSpecialDatesForParking")
                    .toUriString());
            List<ParkingSpecialDate> dates = parkingService.addSpecialDatesForParking(parkingSpecialDates);
            return ResponseEntity.created(uri).body(dates);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @GetMapping("/getParkingDetail")
    public ResponseEntity<ResponseParking> getParkingDetail(@RequestParam int parkingID,
                                                            HttpServletResponse response,
                                                            HttpServletRequest request) {
        try {
            ResponseParking parkingDetail = parkingService.getParkingDetail(parkingID);
            return ResponseEntity.ok(parkingDetail);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @GetMapping("/getListParking")
    public ResponseEntity<Page<ResponseParking>> getListParking(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size,
            HttpServletResponse response,
            HttpServletRequest request) {
        try {
            Page<ResponseParking> parkingPage = parkingService.getListParking(page, size);
            return ResponseEntity.ok(parkingPage);
        } catch (ApiRequestException e) {
            throw e;
        }
    }


}
