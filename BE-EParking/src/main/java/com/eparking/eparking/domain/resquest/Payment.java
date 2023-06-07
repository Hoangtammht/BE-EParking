package com.eparking.eparking.domain.resquest;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @ApiModelProperty(example= "100000")
    private String amountParam;

    public void User() {
        this.amountParam = "10000";
    }
}
