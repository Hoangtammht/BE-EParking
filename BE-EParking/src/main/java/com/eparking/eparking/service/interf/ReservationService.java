package com.eparking.eparking.service.interf;

import com.eparking.eparking.domain.Reservation;
import com.eparking.eparking.domain.response.ResponseReservation;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReservationService {
    Reservation getReservationDetailByReservationID(int reserveID);

    Page<ResponseReservation> getListOrderByUserAndStatusID(int userID, int statusID, int size, int pag);
}
