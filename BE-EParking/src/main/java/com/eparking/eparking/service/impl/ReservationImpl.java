package com.eparking.eparking.service.impl;

import com.eparking.eparking.dao.ReservationMapper;
import com.eparking.eparking.domain.Reservation;
import com.eparking.eparking.domain.response.ResponseReservation;
import com.eparking.eparking.exception.ApiRequestException;
import com.eparking.eparking.service.interf.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationImpl implements ReservationService {
    private final ReservationMapper reservationMapper;
    @Override
    public Reservation getReservationDetailByReservationID(int reserveID) {
        try{
            return reservationMapper.getReservationDetailByReservationID(reserveID);
        }catch (Exception e){
            throw new ApiRequestException("Fail to get detail reservation: " + e);
        }
    }

    @Override
    public Page<ResponseReservation> getListOrderByUserAndStatusID(int userID, int statusID, int size, int page) {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String a = authentication.getName();
            Pageable pageable = PageRequest.of(page, size);
            int offset = (page-1) * size;
            List<ResponseReservation> RepoReservation = reservationMapper.getListOrderByUserAndStatusID(userID,statusID,size,offset);
            Long totalCount = reservationMapper.getNumberOfListOrder(userID,statusID);
            return new PageImpl<>(RepoReservation,pageable,totalCount);
        }catch (Exception e){
            throw new ApiRequestException("Failed to get the list order by this user: " + e);
        }
    }
}
