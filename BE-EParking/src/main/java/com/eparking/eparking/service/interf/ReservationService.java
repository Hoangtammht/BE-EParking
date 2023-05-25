package com.eparking.eparking.service.interf;

import com.eparking.eparking.domain.Reservation;

public interface ReservationService {
    Reservation getReservationDetailByReservationID(int reserveID);
}
