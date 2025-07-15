package com.momsdeli.backend.service.impl;

import com.momsdeli.backend.dto.CartItemDTO;
import com.momsdeli.backend.exceptions.ResourceNotFoundException;
import com.momsdeli.backend.mapping.CartItemMapping;
import com.momsdeli.backend.model.CartItem;
import com.momsdeli.backend.repositories.CartItemRepo;
import com.momsdeli.backend.service.CartItemService;
import com.momsdeli.backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepo cartItemRepo;

    @Autowired
    private CartItemMapping cartItemMapping;

    @Override
    public CartItemDTO deleteCartItem(Long cartItemId) {
        CartItem cartItem=cartItemRepo.findById(cartItemId).orElseThrow(()->new ResourceNotFoundException("Cart-Item",cartItemId,"Cart-Item-ID"));
        cartItemRepo.delete(cartItem);
        return cartItemMapping.toCartItemDTO(cartItem);
    }

    @Override
    public Page<CartItemDTO> getAllCarts(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));  // Pagination and Sorting
        Page<CartItem> cartItemPage = cartItemRepo.findAll(pageable);  // Fetch paginated CartItems from the repository

        // Convert Page<CartItem> to Page<CartItemDTO>
        return cartItemPage.map(cartItemMapping::toCartItemDTO);
    }


    @Override
    public CartItemDTO getCartItemById(Long cartItemId) {
        CartItem cartItem=cartItemRepo.findById(cartItemId).orElseThrow(()->new ResourceNotFoundException("Cart-Item",cartItemId,"Cart-Item-ID"));
        return cartItemMapping.toCartItemDTO(cartItem);
    }
}
