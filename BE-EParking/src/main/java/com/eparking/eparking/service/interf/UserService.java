package com.eparking.eparking.service.interf;

import com.eparking.eparking.domain.User;
import com.eparking.eparking.domain.response.ResponseUser;
import com.eparking.eparking.domain.resquest.UpdateUser;

public interface UserService {

    User findUserByPhoneNumber(String phoneNumber);

    ResponseUser findResponseUserByPhone(String phoneNumber);

    User updateUserByPhoneNumber(UpdateUser updateUser);

    User createSupplier(User user);

    void updateWalletForUser(String responseCode,Long wallet);
}
