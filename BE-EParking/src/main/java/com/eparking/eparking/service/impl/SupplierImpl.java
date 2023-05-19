package com.eparking.eparking.service.impl;

import com.eparking.eparking.dao.SupplierMapper;
import com.eparking.eparking.domain.Customer;
import com.eparking.eparking.domain.Supplier;
import com.eparking.eparking.domain.response.ResponseSupplier;
import com.eparking.eparking.service.interf.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class SupplierImpl implements SupplierService {

    private final SupplierMapper supplierMapper;

    @Override
    public Supplier findSupplierByPhoneNumber(String phoneNumber) {
        return supplierMapper.findSupplierByPhoneNumber(phoneNumber);
    }

    @Override
    public ResponseSupplier findResponseSupplierByPhone(String phoneNumber) {
        return supplierMapper.findResponseSupplierByPhone(phoneNumber);
    }


}
