package com.eparking.eparking.dao;

import com.eparking.eparking.domain.resquest.TransactionData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {
    void insertTransaction(TransactionData transactionData);

}
