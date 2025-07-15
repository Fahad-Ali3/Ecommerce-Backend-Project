package com.momsdeli.backend.service;

import com.momsdeli.backend.dto.CartDTO;
import com.momsdeli.backend.dto.CartItemDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CartItemService {

    CartItemDTO deleteCartItem(Long cartItemId);
    Page<CartItemDTO> getAllCarts(int page, int size, String sortBy);
    CartItemDTO getCartItemById(Long cartItemId);
}
