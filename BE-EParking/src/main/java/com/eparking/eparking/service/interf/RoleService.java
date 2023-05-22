package com.eparking.eparking.service.interf;

import com.eparking.eparking.domain.UserRole;

import java.util.List;

public interface RoleService {
    List<UserRole> findRoleByPhoneNumber(String phoneNumber);
    void insertUserRole(int roleID, String phoneNumber);
}
