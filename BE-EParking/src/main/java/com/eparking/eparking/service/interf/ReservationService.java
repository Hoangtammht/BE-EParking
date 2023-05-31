package com.eparking.eparking.service.interf;

import com.eparking.eparking.domain.Reservation;
import com.eparking.eparking.domain.response.ResponseReservation;
import com.eparking.eparking.domain.resquest.RequestReservation;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReservationService {
    Reservation getReservationDetailByReservationID(int reserveID);
    Page<ResponseReservation> getListOrderByUserAndStatusID(int statusID, int size, int pag);
    ResponseReservation createReservation(RequestReservation requestReservation);
    ResponseReservation getReservationDetail(int reserveID);
    ResponseReservation updateReservationStatus(int reserveID, int status);

}
