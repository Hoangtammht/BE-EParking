package com.eparking.eparking.controller;

import com.eparking.eparking.domain.response.ResponseParking;
import com.eparking.eparking.domain.response.ResponseReservation;
import com.eparking.eparking.exception.ApiRequestException;
import com.eparking.eparking.service.interf.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@CrossOrigin
@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {
        private final ReservationService reservationService;
    @GetMapping("/getListOrder")
    public ResponseEntity<Page<ResponseReservation>> getListParking(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "2") int size,
            @RequestParam int userID,
            @RequestParam int statusID) {
        try {
            Page<ResponseReservation> reservations = reservationService.getListOrderByUserAndStatusID(userID,statusID,size,page);
            return ResponseEntity.ok(reservations);
        } catch (ApiRequestException e) {
            throw e;
        }
    }
}
