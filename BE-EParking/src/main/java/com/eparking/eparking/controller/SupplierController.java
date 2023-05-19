package com.eparking.eparking.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.eparking.eparking.domain.Customer;
import com.eparking.eparking.domain.Supplier;
import com.eparking.eparking.domain.response.ResponseCustomer;
import com.eparking.eparking.domain.response.ResponseSupplier;
import com.eparking.eparking.service.interf.SupplierService;
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
@RequestMapping("/supplier")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;
    private final AuthenticationManager authenticationManager;

    @Value("${SECRET_KEY}")
    private String secret;

    @PostMapping("/loginSupplier")
    public void loginCustomer(@RequestParam String phoneSupplier,
                              @RequestParam String password,
                              HttpServletResponse response,
                              HttpServletRequest request) throws IOException {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret.getBytes());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(phoneSupplier, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String name = authentication.getName();
            Supplier supplier = supplierService.findSupplierByPhoneNumber(name);
            String access_token = JWT.create()
                    .withSubject(supplier.getPhoneSupplier())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 30*60*1000))
                    .withIssuer(request.getRequestURL().toString())
                    .sign(algorithm);
            String refresh_token = JWT.create()
                    .withSubject(supplier.getPhoneSupplier())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 30*60*1000))
                    .withIssuer(request.getRequestURL().toString())
                    .sign(algorithm);
            ResponseSupplier responseSupplier = supplierService.findResponseSupplierByPhone(supplier.getPhoneSupplier());
            Map<String, Object> tokens = new HashMap<>();
            tokens.put("access_token", access_token);
            tokens.put("refresh_token", refresh_token);
            tokens.put("Supplier", responseSupplier);
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
