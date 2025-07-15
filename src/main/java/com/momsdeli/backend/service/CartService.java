package com.momsdeli.backend.service;


import com.momsdeli.backend.dto.CartDTO;
import com.momsdeli.backend.model.Cart;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CartService {

    CartDTO saveCart(CartDTO cartDTO);
    CartDTO deleteCart(Long cartId);
    CartDTO updateCart(CartDTO cartDTO,Long cartId);
    CartDTO getCartById(Long cartId);
    Page<CartDTO> getCarts(int page, int size, String sortBy);
}
