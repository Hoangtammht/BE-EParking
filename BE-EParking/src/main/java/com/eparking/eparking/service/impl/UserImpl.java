package com.eparking.eparking.service.impl;

import com.eparking.eparking.dao.UserMapper;
import com.eparking.eparking.domain.User;
import com.eparking.eparking.domain.UserRole;
import com.eparking.eparking.domain.response.ResponseUser;
import com.eparking.eparking.domain.resquest.UpdateUser;
import com.eparking.eparking.exception.ApiRequestException;
import com.eparking.eparking.service.interf.RoleService;
import com.eparking.eparking.service.interf.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserImpl implements UserDetailsService, UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;
    
    private final RoleService roleService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findUserByPhoneNumber(username);
        if(user == null){
                log.error("User not found in the database");
                throw new UsernameNotFoundException("User not found in the database");
        }else{
            log.info("User found in the database: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        List<UserRole> userRole = roleService.findRoleByPhoneNumber(user.getPhoneNumber());
        for (UserRole roleName: userRole) {
            authorities.add(new SimpleGrantedAuthority(roleName.getRoleName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getPhoneNumber(), user.getPassword(), authorities);
    }

    @Override
    public User findUserByPhoneNumber(String phoneNumber) {
        return userMapper.findUserByPhoneNumber(phoneNumber);
    }

    @Override
    public ResponseUser findResponseUserByPhone(String phoneNumber) {
        return userMapper.findResponseUserByPhone(phoneNumber);
    }

    @Override
    public User updateUserByPhoneNumber(UpdateUser updateUser) {
        try {
            userMapper.updateUserByPhoneNumber(updateUser);
            return findUserByPhoneNumber(updateUser.getPhoneNumber());
        } catch (Exception e) {
            throw new RuntimeException("Failed to update user by phone number");
        }
    }

    @Override
    @Transactional
    public User createSupplier(User user) {
        User existingSupplier = userMapper.findUserByPhoneNumber(user.getPhoneNumber());
        if (existingSupplier != null ) {
            throw new ApiRequestException("The supplier already exists");
        }
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userMapper.createSupplier(user);
            roleService.insertUserRole(1, user.getUserID());
        } catch (Exception e) {
            throw new ApiRequestException("Failed to create supplier: " + e);
        }
        return user;
    }

}
