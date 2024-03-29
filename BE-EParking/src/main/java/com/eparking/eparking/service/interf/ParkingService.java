package com.eparking.eparking.service.interf;

import com.eparking.eparking.domain.Parking;
import com.eparking.eparking.domain.ParkingDate;
import com.eparking.eparking.domain.ParkingSpecialDate;
import com.eparking.eparking.domain.SpecialDate;
import com.eparking.eparking.domain.response.ResponseParking;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ParkingService {

    ResponseParking createParking(Parking parking);

    List<ParkingDate> addDatesForParking(List<ParkingDate> parkingDates);

    List<ParkingSpecialDate> addSpecialDatesForParking(List<ParkingSpecialDate> parkingSpecialDates);

    SpecialDate createSpecialDate(SpecialDate specialDate);

    ResponseParking getParkingDetail(int parkingID);

    Page<ResponseParking> getListParking(int page, int size);
}
