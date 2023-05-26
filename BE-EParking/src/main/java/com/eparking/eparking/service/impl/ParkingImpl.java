package com.eparking.eparking.service.impl;

import com.eparking.eparking.dao.ParkingMapper;
import com.eparking.eparking.domain.Parking;
import com.eparking.eparking.domain.ParkingDate;
import com.eparking.eparking.domain.ParkingSpecialDate;
import com.eparking.eparking.domain.SpecialDate;
import com.eparking.eparking.domain.response.ResponseParking;
import com.eparking.eparking.exception.ApiRequestException;
import com.eparking.eparking.service.interf.ParkingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParkingImpl implements ParkingService {

    private final ParkingMapper parkingMapper;

    @Override
    @Transactional
    public ResponseParking createParking(Parking parking) {
        try {
            parkingMapper.createParking(parking);
            return parkingMapper.findParkingByParkingID(parking.getParkingID());
        } catch (Exception e) {
            throw new ApiRequestException("Failed to create parking: " + e);
        }
    }

    @Override
    public SpecialDate createSpecialDate(SpecialDate specialDate) {
        try {
            parkingMapper.createSpecialDate(specialDate);
            return specialDate;
        } catch (Exception e) {
            throw new ApiRequestException("Failed to create parking: " + e);
        }
    }

    @Override
    public List<ParkingDate> addDatesForParking(List<ParkingDate> parkingDates) {
        try {
            parkingMapper.addDatesForParking(parkingDates);
            return parkingDates;
        } catch (Exception e) {
            throw new ApiRequestException("Failed to add date for Parking : " + e);
        }
    }

    @Override
    public List<ParkingSpecialDate> addSpecialDatesForParking(List<ParkingSpecialDate> parkingSpecialDates) {
        try {
            parkingMapper.addSpecialDatesForParking(parkingSpecialDates);
            return parkingSpecialDates;
        } catch (Exception e) {
            throw new ApiRequestException("Failed to add date for Special Parking : " + e);
        }
    }

    @Override
    public ResponseParking getParkingDetail(int parkingID) {
        try {
            ResponseParking parkingDetail = parkingMapper.findParkingByParkingID(parkingID);
            return parkingDetail;
        } catch (Exception e) {
            throw new ApiRequestException("Failed to get parking detail: " + e);
        }
    }

    @Override
    public Page<ResponseParking> getListParking(int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            int offset = page * size;
            List<ResponseParking> parkingList = parkingMapper.getListParking(size, offset);
            long totalCount = parkingMapper.getNumberOfParkings();
            return new PageImpl<>(parkingList, pageable, totalCount);
        } catch (Exception e) {
            throw new ApiRequestException("Failed to get all Parking: " + e);
        }
    }

}
