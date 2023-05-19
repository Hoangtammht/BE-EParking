package com.eparking.eparking.service.interf;

import com.eparking.eparking.domain.Customer;
import com.eparking.eparking.domain.response.ResponseCustomer;

public interface CustomerService {

    Customer findCustomerByPhoneNumber(String phoneNumber);

    ResponseCustomer findResponseCustomerByPhone(String phoneNumber);

}
