package com.eparking.eparking.dao;

import com.eparking.eparking.domain.Reservation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationMapper {
    Reservation getReservationDetailByReservationID(int reserveID);
}
