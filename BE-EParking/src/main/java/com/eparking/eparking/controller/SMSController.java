package com.eparking.eparking.controller;

import com.eparking.eparking.domain.response.ResponseCheckOTP;
import com.eparking.eparking.domain.response.ResponseSendOTP;
import com.eparking.eparking.service.impl.ESMService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import com.eparking.eparking.service.impl.ESMService;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

@Controller
@RequestMapping("/sms")
@RequiredArgsConstructor
public class SMSController {
    private final ESMService esmService;


    @GetMapping("/send-jsonOTP")
    @ResponseBody
    public ResponseSendOTP sendGetJSONOTP(@RequestParam String phone) throws Exception {
        return esmService.sendOTP(phone);
    }
    @GetMapping("/checkOTP")
    @ResponseBody
    public ResponseCheckOTP checkcode(@RequestParam String phone, @RequestParam String message) throws Exception {
        return esmService.checkOTP(phone,message);
    }
}
