package com.eparking.eparking.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private String phoneCustomer;

    private String password;

    private String customerName;

    private String email;

}
