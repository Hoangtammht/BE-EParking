package com.eparking.eparking.service.impl;

import com.eparking.eparking.dao.CustomerMapper;
import com.eparking.eparking.domain.Customer;
import com.eparking.eparking.domain.Supplier;
import com.eparking.eparking.domain.response.ResponseCustomer;
import com.eparking.eparking.service.interf.CustomerService;
import com.eparking.eparking.service.interf.SupplierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerImpl implements UserDetailsService, CustomerService {

    private final PasswordEncoder passwordEncoder;

    private final CustomerMapper customerMapper;

    private final SupplierService supplierService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerMapper.findCustomerByPhoneNumber(username);
        if(customer == null){
            Supplier supplier = supplierService.findSupplierByPhoneNumber(username);
            if(supplier == null){
                log.error("User not found in the database");
                throw new UsernameNotFoundException("User not found in the database");
            }else{
                log.info("User found in the database: {}", username);
            }
            return new org.springframework.security.core.userdetails.User(supplier.getPhoneSupplier(), supplier.getPassword(), Collections.emptyList());
        }else{
            log.info("User found in the database: {}", username);
        }
        return new org.springframework.security.core.userdetails.User(customer.getPhoneCustomer(), customer.getPassword(), Collections.emptyList());
    }

    @Override
    public Customer findCustomerByPhoneNumber(String phoneNumber) {
        return customerMapper.findCustomerByPhoneNumber(phoneNumber);
    }

    @Override
    public ResponseCustomer findResponseCustomerByPhone(String phoneNumber) {
        return customerMapper.findResponseCustomerByPhone(phoneNumber);
    }


}
