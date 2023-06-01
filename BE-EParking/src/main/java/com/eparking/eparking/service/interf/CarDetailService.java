package com.eparking.eparking.service.interf;

import com.eparking.eparking.domain.response.ResponseCarDetail;
import com.eparking.eparking.domain.response.ResponseCarInParking;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CarDetailService {

    ResponseCarDetail addCar(String licensePlate);

    ResponseCarDetail removeCar(int carID);

    Page<ResponseCarInParking> findCarsInParkingByStatus(int status, int page, int size);

    List<ResponseCarDetail> getListCarOfUser();
}
