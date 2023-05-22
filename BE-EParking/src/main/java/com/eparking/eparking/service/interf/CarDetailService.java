package com.eparking.eparking.service.interf;

import com.eparking.eparking.domain.CarDetail;
import com.eparking.eparking.domain.resquest.ResquestCar;

import java.util.List;

public interface CarDetailService {

    List<CarDetail> addCar(CarDetail carDetail);

    void removeCar(int carID);
}
