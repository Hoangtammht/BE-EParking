package com.eparking.eparking.service.interf;

import com.eparking.eparking.domain.CarDetail;
import com.eparking.eparking.domain.response.ResponseCarInParking;
import com.eparking.eparking.domain.resquest.ResquestCar;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CarDetailService {

    List<CarDetail> addCar(CarDetail carDetail);

    void removeCar(int carID);

    Page<ResponseCarInParking> findCarsInParkingByStatus(int status, int page, int size);
}
