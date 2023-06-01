package com.eparking.eparking.service.interf;

import com.eparking.eparking.domain.response.ResponseCarDetail;
import com.eparking.eparking.domain.response.ResponseCarInParking;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CarDetailService {

    List<ResponseCarDetail> addCar(String licensePlate);

    void removeCar(int carID);

    Page<ResponseCarInParking> findCarsInParkingByStatus(int status, int page, int size);

    List<ResponseCarDetail> getListCarOfUser();
}
