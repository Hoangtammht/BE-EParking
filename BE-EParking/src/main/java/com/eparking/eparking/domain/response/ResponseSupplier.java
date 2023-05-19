package com.eparking.eparking.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSupplier {
    private String phoneSupplier;
    private String supplierName;
    private String identifyCard;
}
