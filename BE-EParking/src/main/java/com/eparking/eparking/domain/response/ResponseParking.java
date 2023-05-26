package com.eparking.eparking.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseParking {
    private int parkingID;
    private int userID;
    private String phoneNumber;
    private String methodName;
    private String parkingName;
    private String description;
    private String images;
    private String address;
    private double latitude;
    private double longitude;
    private int pricing;
    private int park;
    private int status;

}
