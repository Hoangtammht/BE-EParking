package com.eparking.eparking.dao;

import com.eparking.eparking.domain.Reservation;
import com.eparking.eparking.domain.response.ResponseReservation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {
    Reservation getReservationDetailByReservationID(int reserveID);

    List<ResponseReservation> getListOrderByUserAndStatusID(int userID, int statusID, int size, int offset);

    Long getNumberOfListOrder(int userID, int statusID);
}