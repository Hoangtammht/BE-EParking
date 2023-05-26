package com.eparking.eparking.dao;

import com.eparking.eparking.domain.CarDetail;
import com.eparking.eparking.domain.response.ResponseCarDetail;
import com.eparking.eparking.domain.response.ResponseCarInParking;
import com.eparking.eparking.domain.resquest.ResquestCar;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CarDetailMapper {

    List<ResponseCarDetail> findCarDetailByUserID(int userID);
    void addCar(CarDetail carDetail);
    void removeCar(int carID);
    List<ResponseCarInParking> findCarsInParkingByStatus(int status, int size, int offset);

    long getNumberOfReservationByStatus(int status);
}
