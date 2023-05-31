package com.eparking.eparking.domain.resquest;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @ApiModelProperty(example= "Order")
    private String ordertypeParam;
    @ApiModelProperty(example= "10000")
    private long amountParam;
    @ApiModelProperty(example= "NCB")
    private String bankCodeParam;
    @ApiModelProperty(example= "1.0.0.0")
    private String vnp_IpAddr;
    @ApiModelProperty(example= "VN")
    private String locate;

    public void User() {
        this.ordertypeParam = "Order";
        this.amountParam = 1000;
        this.bankCodeParam = "NCB";
        this.vnp_IpAddr = "1.0.0.0";
        this.locate = "VN";
    }
}
