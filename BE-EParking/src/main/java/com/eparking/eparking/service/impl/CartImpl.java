package com.eparking.eparking.service.impl;

import com.eparking.eparking.dao.CartMapper;
import com.eparking.eparking.domain.Cart;
import com.eparking.eparking.exception.ApiRequestException;
import com.eparking.eparking.service.interf.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartImpl implements CartService {
    private final CartMapper cartMapper;
    @Override
    public List<Cart> getListCartByUserID(int userID) {
            List<Cart> cart = cartMapper.getListCartByUserID(userID);
            if(cart.isEmpty() || cart.size() == 0){
                throw new ApiRequestException("This cart get by this user is empty!");
            }
        try{
            return cart;
        }catch (Exception e){
            throw new ApiRequestException("Fail to get list cart by this user");
        }
    }
}
