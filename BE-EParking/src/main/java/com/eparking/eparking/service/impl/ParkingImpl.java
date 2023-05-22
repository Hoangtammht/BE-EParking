package com.eparking.eparking.service.impl;

import com.eparking.eparking.dao.ParkingMapper;
import com.eparking.eparking.domain.Parking;
import com.eparking.eparking.exception.ApiRequestException;
import com.eparking.eparking.service.interf.ParkingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParkingImpl implements ParkingService {

    private final ParkingMapper parkingMapper;

    @Override
    public Parking createParking(Parking parking) {
        try {
            parkingMapper.createParking(parking);
            return parkingMapper.findParkingByParkingID(parking.getParkingID());
        } catch (Exception e) {
            throw new ApiRequestException("Failed to create parking: " + e);
        }
    }

}
