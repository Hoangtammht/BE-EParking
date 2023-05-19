package com.eparking.eparking.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.eparking.eparking.domain.Customer;
import com.eparking.eparking.domain.response.ResponseCustomer;
import com.eparking.eparking.service.interf.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final AuthenticationManager authenticationManager;
    @Value("${SECRET_KEY}")
    private String secret;

    @PostMapping("/loginCustomer")
    public void loginCustomer(@RequestParam String phoneCustomer,
                          @RequestParam String password,
                          HttpServletResponse response,
                          HttpServletRequest request) throws IOException {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(phoneCustomer, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String name = authentication.getName();
            Customer customer = customerService.findCustomerByPhoneNumber(name);
            String access_token = JWT.create()
                    .withSubject(customer.getPhoneCustomer())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 30*60*1000))
                    .withIssuer(request.getRequestURL().toString())
                    .sign(algorithm);
            String refresh_token = JWT.create()
                    .withSubject(customer.getPhoneCustomer())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 30*60*1000))
                    .withIssuer(request.getRequestURL().toString())
                    .sign(algorithm);
            ResponseCustomer responseCustomer = customerService.findResponseCustomerByPhone(customer.getPhoneCustomer());
            Map<String, Object> tokens = new HashMap<>();
            tokens.put("access_token", access_token);
            tokens.put("refresh_token", refresh_token);
            tokens.put("Customer", responseCustomer);
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

}
