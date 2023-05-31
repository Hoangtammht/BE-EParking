package com.eparking.eparking.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.eparking.eparking.domain.Role;
import com.eparking.eparking.domain.User;
import com.eparking.eparking.domain.UserRole;
import com.eparking.eparking.domain.response.ResponseUser;
import com.eparking.eparking.domain.resquest.LoginUser;
import com.eparking.eparking.domain.resquest.RequestCreateUser;
import com.eparking.eparking.domain.resquest.UpdateUser;
import com.eparking.eparking.exception.ApiRequestException;
import com.eparking.eparking.service.interf.RoleService;
import com.eparking.eparking.service.interf.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final RoleService roleService;

    private final AuthenticationManager authenticationManager;
    @Value("${SECRET_KEY}")
    private String secret;

    @PostMapping("/loginUser")
    public void loginUser(@RequestBody LoginUser loginUser,
                          HttpServletResponse response,
                          HttpServletRequest request) throws IOException {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUser.getPhoneNumber(), loginUser.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String name = authentication.getName();
            User user = userService.findUserByPhoneNumber(name);
            List<UserRole> userRole = roleService.findRoleByPhoneNumber(user.getPhoneNumber());
            List<String> roleNames = new ArrayList<>();
            for (UserRole role : userRole) {
                roleNames.add(role.getRoleName());
            }
            String access_token = JWT.create()
                    .withSubject(user.getPhoneNumber())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim("roles", roleNames)
                    .sign(algorithm);
            ResponseUser responseUser = userService.findResponseUserByPhone(user.getPhoneNumber());
            responseUser.setRoleName(roleNames);
            Map<String, Object> tokens = new HashMap<>();
            tokens.put("access_token", access_token);
            tokens.put("User", responseUser);
            response.setContentType("application/json");
            new ObjectMapper().writeValue(response.getOutputStream(), tokens);
        }catch (Exception e){
            response.setHeader("error", e.getMessage());
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            Map<String, String> error = new HashMap<>();
            error.put("error_message", e.getMessage());
            response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }
    }

    @PutMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody UpdateUser updateUser,
                                           HttpServletResponse response,
                                           HttpServletRequest request) {
        try {
            User updatedUser = userService.updateUserByPhoneNumber(updateUser);
            return ResponseEntity.ok().body(updatedUser);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @PostMapping("/registerUser")
    public ResponseEntity<User> createUser(@RequestBody RequestCreateUser user,
                                                 HttpServletResponse response,
                                                 HttpServletRequest request) {
        try {
            User newSupplier = userService.createUser(user);
            return ResponseEntity.ok().body(newSupplier);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @GetMapping("/getUserProfile")
    public ResponseEntity<User> getUserProfile(HttpServletResponse response, HttpServletRequest request) {
        try {
            User newSupplier = userService.getUserProfile();
            return ResponseEntity.ok().body(newSupplier);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

    @GetMapping("/getRoleByUserID")
    public ResponseEntity<List<UserRole>> getRoleByUserID(HttpServletResponse response, HttpServletRequest request){
        try {
            List<UserRole> listRole = userService.getRoleByUserID();
            return ResponseEntity.ok().body(listRole);
        } catch (ApiRequestException e) {
            throw e;
        }
    }

}
