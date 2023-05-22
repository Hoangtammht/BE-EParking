package com.eparking.eparking.service.impl;

import com.eparking.eparking.dao.CarDetailMapper;
import com.eparking.eparking.domain.CarDetail;
import com.eparking.eparking.service.interf.CarDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

}
