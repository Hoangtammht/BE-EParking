package com.eparking.eparking.domain.resquest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCreateUser {
    private String phoneNumber;
    private String password;
    private String fullName;
    private String email;
    private String identifyCard;
    private List<Integer> userRoles;
}
