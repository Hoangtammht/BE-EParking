package com.eparking.eparking.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String phoneNumber;
    private String password;
    private String fullName;
    private String email;
    private String identifyCard;

}
