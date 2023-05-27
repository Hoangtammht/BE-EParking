package com.eparking.eparking.domain.resquest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestSearchNearByParking {
    private double latitude;
    private double longitude;
    private double radius;
    private int page;
    private int size;
    private String sortBy;
}
