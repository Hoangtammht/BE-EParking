package com.eparking.eparking.service.impl;

import com.eparking.eparking.dao.RoleMapper;
import com.eparking.eparking.domain.Role;
import com.eparking.eparking.domain.UserRole;
import com.eparking.eparking.service.interf.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleImpl implements RoleService {
    private final RoleMapper roleMapper;
    @Override
    public List<UserRole> findRoleByPhoneNumber(String phoneNumber) {
        return roleMapper.findRoleByPhoneNumber(phoneNumber);
    }

    @Override
    public void insertUserRole(int roleID, int userID) {
        roleMapper.insertUserRole(roleID, userID);
    }

    @Override
    public boolean findRoleIDForUser(String phoneNumber) {
        return roleMapper.findRoleIDForUser(phoneNumber);
    }
}
