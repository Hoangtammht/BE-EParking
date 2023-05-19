package com.eparking.eparking.dao;

import com.eparking.eparking.domain.Customer;
import com.eparking.eparking.domain.response.ResponseCustomer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper {
    Customer findCustomerByPhoneNumber(String phoneNumber);

    ResponseCustomer findResponseCustomerByPhone(String phoneNumber);
}
