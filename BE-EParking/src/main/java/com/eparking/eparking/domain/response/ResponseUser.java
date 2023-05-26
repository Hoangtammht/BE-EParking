package com.eparking.eparking.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUser {
    private int userID;
    private String phoneNumber;
    private String fullName;
    private String email;
    private String identifyCard;
    private List<String> roleName;

}
