package com.eparking.eparking.dao;

import com.eparking.eparking.domain.Parking;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ParkingMapper {
    void createParking(Parking parking);

    Parking findParkingByParkingID(int parkingID);
}
