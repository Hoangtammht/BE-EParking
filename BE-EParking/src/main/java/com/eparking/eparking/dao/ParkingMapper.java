package com.eparking.eparking.dao;

import com.eparking.eparking.domain.Parking;
import com.eparking.eparking.domain.ParkingDate;
import com.eparking.eparking.domain.ParkingSpecialDate;
import com.eparking.eparking.domain.SpecialDate;
import com.eparking.eparking.domain.response.ResponseParking;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper
public interface ParkingMapper {
    void createParking(Parking parking);
    ResponseParking findParkingByParkingID(int parkingID);
    void addDatesForParking(List<ParkingDate> parkingDates);
    void addSpecialDatesForParking(List<ParkingSpecialDate> parkingSpecialDates);
    List<ParkingDate> showDatesOfParking(int parkingID);
    void createSpecialDate(SpecialDate specialDate);
    List<ResponseParking> getListParking(int size,int offset);
    long getNumberOfParkings();

}
