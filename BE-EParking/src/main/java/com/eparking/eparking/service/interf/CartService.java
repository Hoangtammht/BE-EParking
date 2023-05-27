package com.eparking.eparking.service.interf;

import com.eparking.eparking.domain.Cart;

import java.util.List;

public interface CartService {
    List<Cart> getListCartByUserID (int userID);
}
