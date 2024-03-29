package com.eparking.eparking.domain.resquest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUser {
    private String phoneNumber;

    private String password;

    private String fullName;

    private String email;

    private String identifyCard;


}
