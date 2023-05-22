package com.eparking.eparking.controller;

import com.eparking.eparking.domain.CarDetail;
import com.eparking.eparking.domain.Parking;
import com.eparking.eparking.exception.ApiRequestException;
import com.eparking.eparking.service.interf.ParkingService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<Parking> createParking(@RequestBody Parking parking,
                                                 HttpServletResponse response,
                                                 HttpServletRequest request) {
        try {
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/parking/createParking")
                    .toUriString());
            Parking newParking = parkingService.createParking(parking);
            return ResponseEntity.created(uri).body(newParking);
        } catch (ApiRequestException e) {
            throw e;
        }
    }


}
