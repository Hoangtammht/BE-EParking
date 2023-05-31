package com.eparking.eparking.service.interf;

import com.eparking.eparking.domain.Role;
import com.eparking.eparking.domain.User;
import com.eparking.eparking.domain.UserRole;
import com.eparking.eparking.domain.response.ResponseUser;
import com.eparking.eparking.domain.resquest.RequestCreateUser;
import com.eparking.eparking.domain.resquest.UpdateUser;

import java.util.List;

public interface UserService {

    User findUserByPhoneNumber(String phoneNumber);

    ResponseUser findResponseUserByPhone(String phoneNumber);

    User updateUserByPhoneNumber(UpdateUser updateUser);

    User createUser(RequestCreateUser user);

    User getUserProfile();

    List<UserRole> getRoleByUserID();

}
