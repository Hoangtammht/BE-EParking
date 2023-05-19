package com.eparking.eparking.dao;

import com.eparking.eparking.domain.Supplier;
import com.eparking.eparking.domain.response.ResponseSupplier;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SupplierMapper {
    Supplier findSupplierByPhoneNumber(String phoneNumber);

    ResponseSupplier findResponseSupplierByPhone(String phoneNumber);
}
