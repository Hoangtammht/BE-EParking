package com.eparking.eparking.controller;

import com.eparking.eparking.domain.response.ResponseReservation;
import com.eparking.eparking.domain.resquest.RequestUpdatestatus;
import com.eparking.eparking.service.interf.ReservationService;
import com.eparking.eparking.service.interf.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
@RequiredArgsConstructor
@Controller
public class SocketController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ReservationService reservationService;
    private final UserService userService;

    @MessageMapping("/send")
    public void sendMessage(@RequestBody RequestUpdatestatus requestReservation) {
        ResponseReservation responseReservation = reservationService.updateStatus(requestReservation.getStatusID(), requestReservation.getReserveID());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userID = userService.findUserByPhoneNumber(authentication.getName()).getUserID();
        String serverResponse = null;
        if (requestReservation.getStatusID() == 1) {
            serverResponse = "Đã gửi";
        } else if (requestReservation.getStatusID() == 3) {
            serverResponse = "Đã rời";
        }
        messagingTemplate.convertAndSendToUser(String.valueOf(userID), "/status/response", serverResponse);
    }
}
