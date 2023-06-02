package com.eparking.eparking.controller;

import com.eparking.eparking.domain.resquest.Payment;
import com.eparking.eparking.exception.ApiRequestException;
import com.eparking.eparking.paymenConfig.VNpayConfig;
import com.eparking.eparking.service.impl.PaymentService;
import com.eparking.eparking.service.interf.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.eparking.eparking.paymenConfig.VNpayConfig.vnp_Version;

@CrossOrigin
@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final UserService UserService;
    @GetMapping("/createPayment")
    public ResponseEntity<?> createPayment(Payment payment) throws UnsupportedEncodingException {
       try{
           return paymentService.createPayment(payment);
       }catch (ApiRequestException e){
            throw e;
       }
    }
        @PutMapping("/updateWallet")
    public void updateWallet(@RequestParam(value = "vpn_ResponseCode") String ResponseCode,
                             @RequestParam(value = "vpn_Amount") Long amount){
        try{
            UserService.updateWalletForUser(ResponseCode,amount);
        }catch (ApiRequestException e){
            throw e;
        }
    }
    @GetMapping("/IPN")
    public void handleIPN (HttpServletRequest request){
        try{
            paymentService.handleIPN(request);
        }catch (Exception e){
            throw e;
        }
    }
}
