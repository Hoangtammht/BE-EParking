package com.eparking.eparking.service.impl;

import com.eparking.eparking.dao.ReservationMapper;
import com.eparking.eparking.domain.Reservation;
import com.eparking.eparking.exception.ApiRequestException;
import com.eparking.eparking.service.interf.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationImpl implements ReservationService {
    private final ReservationMapper reservationMapper;
    @Override
    public Reservation getReservationDetailByReservationID(int reserveID) {
        try{
            return reservationMapper.getReservationDetailByReservationID(reserveID);
        }catch (Exception e){
            throw new ApiRequestException("Fail to get detail reservation: " + e);
        }
    }
}
