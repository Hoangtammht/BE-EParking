package com.eparking.eparking.controller;

import com.eparking.eparking.domain.Cart;
import com.eparking.eparking.domain.Reservation;
import com.eparking.eparking.domain.response.ResponseCart;
import com.eparking.eparking.domain.response.ResponseCartWithError;
import com.eparking.eparking.exception.ApiRequestException;
import com.eparking.eparking.service.interf.CartService;
import com.eparking.eparking.service.interf.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final ReservationService reservationService;
    @GetMapping("/getListReservation")
    public ResponseEntity<ResponseCartWithError> getListReservation(@RequestParam String phoneNumber){
        try {
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/cart/getListReservation")
                    .toUriString());
            ResponseCartWithError responeCart = new ResponseCartWithError();
            ResponseCart responeCartIN = new ResponseCart();
            List<Cart> listCart = cartService.getListCartByUser(phoneNumber);
            if (listCart.isEmpty() || listCart.size() == 0) {
                responeCart.setError("This cart is empty!");
                return ResponseEntity.created(uri).body(responeCart);
            }
            int count = 0;
            List<Reservation> reservations = new ArrayList<>();
            for (Cart cart :
                    listCart) {
                count++;
                reservations.add(reservationService.getReservationDetailByReservationID(cart.getReserveID()));
            }
            responeCartIN.setCarsNumber(count);
            responeCartIN.setPhoneNumber(phoneNumber);
            responeCartIN.setReservationDetail(reservations);
            responeCart.setCart(responeCartIN);
            return ResponseEntity.created(uri).body(responeCart);
        }catch (ApiRequestException e){
            throw e;
        }
    }
}
