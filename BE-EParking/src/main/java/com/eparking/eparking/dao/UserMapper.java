package com.eparking.eparking.dao;

import com.eparking.eparking.domain.User;
import com.eparking.eparking.domain.response.ResponseUser;
import com.eparking.eparking.domain.resquest.UpdateUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findUserByPhoneNumber(String phoneNumber);
    ResponseUser findResponseUserByPhone(String phoneNumber);
    void updateUserByPhoneNumber(UpdateUser updateUser, String phoneNumber);
    void createSupplier(User user);
    void updateWalletForUser(int userID,Long wallet);
}
