package com.eparking.eparking.controller;

import com.eparking.eparking.domain.Cart;
import com.eparking.eparking.domain.Reservation;
import com.eparking.eparking.domain.response.ResponseCart;
import com.eparking.eparking.exception.ApiRequestException;
import com.eparking.eparking.service.interf.CartService;
import com.eparking.eparking.service.interf.ReservationService;
import com.eparking.eparking.service.interf.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final ReservationService reservationService;
    private final UserService userService;
    @GetMapping("/getListReservation")
    public ResponseEntity<ResponseCart> getListReservation(){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            int userID = userService.findUserByPhoneNumber(authentication.getName()).getUserID();
            ResponseCart responeCart = new ResponseCart();
            List<Cart> listCart = cartService.getListCartByUserID();
            int count = 0;
            List<Reservation> reservations = new ArrayList<>();
            for (Cart cart :
                    listCart) {
                count++;
                reservations.add(reservationService.getReservationDetailByReservationID(cart.getReserveID()));
            }
            responeCart.setCarsNumber(count);
            responeCart.setUserID(userID);
            responeCart.setReservationDetail(reservations);
            return ResponseEntity.ok(responeCart);
            }catch (ApiRequestException e){
            throw e;
        }
    }
}
