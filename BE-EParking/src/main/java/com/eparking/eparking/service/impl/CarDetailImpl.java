package com.eparking.eparking.service.impl;

import com.eparking.eparking.dao.CarDetailMapper;
import com.eparking.eparking.domain.CarDetail;
import com.eparking.eparking.domain.response.ResponseCarInParking;
import com.eparking.eparking.exception.ApiRequestException;
import com.eparking.eparking.service.interf.CarDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarDetailImpl implements CarDetailService {

    private final CarDetailMapper carDetailMapper;

    @Override
    public List<CarDetail> addCar(CarDetail carDetail) {
        try {
            carDetailMapper.addCar(carDetail);
            return carDetailMapper.findCarDetailByPhoneNumber(carDetail.getPhoneNumber());
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
            long totalCount = carDetailMapper.getNumberOfReservationByStatus(status);
            return new PageImpl<>(carsParking, pageable, totalCount);
        } catch (Exception e) {
            throw new ApiRequestException("Failed to find the list cars by revenue status of Supplier");
        }
    }

}
