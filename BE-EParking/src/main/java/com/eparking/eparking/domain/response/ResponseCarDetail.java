package com.eparking.eparking.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCarDetail {
    private int carID;
    private String phoneNumber;
    private String licensePlate;

}
