package com.eparking.eparking.domain.resquest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestNewPassword {
    private String phoneNumber;
    private String password;
}
