package com.eparking.eparking.domain.resquest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCarsInParking {
    private int status;
    private int size;
    private int page;

}
