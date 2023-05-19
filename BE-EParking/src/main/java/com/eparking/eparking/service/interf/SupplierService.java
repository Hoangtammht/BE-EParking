package com.eparking.eparking.service.interf;

import com.eparking.eparking.domain.Supplier;
import com.eparking.eparking.domain.response.ResponseSupplier;


public interface SupplierService {
    Supplier findSupplierByPhoneNumber(String phoneNumber);

    ResponseSupplier findResponseSupplierByPhone(String phoneNumber);
}
