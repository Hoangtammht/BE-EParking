package com.eparking.eparking.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    private String phoneSupplier;
    private String password;
    private String supplierName;
    private String identifyCard;

}
