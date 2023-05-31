package com.eparking.eparking.domain.resquest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestReservation {
    private int parkingID;
    private LocalDateTime startDateTime;
    private LocalDateTime endDatetime;
    private int totalPrice;
    private int statusID;
    private int carID;

}
