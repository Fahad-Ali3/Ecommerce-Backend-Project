package com.momsdeli.backend.mapping;

import com.momsdeli.backend.dto.CartDTO;
import com.momsdeli.backend.exceptions.ResourceNotFoundException;
import com.momsdeli.backend.model.Cart;
import com.momsdeli.backend.model.CartItem;
import com.momsdeli.backend.model.OrderItem;
import com.momsdeli.backend.model.User;
import com.momsdeli.backend.repositories.UserRepo;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapping {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CartItemMapping cartItemMapping;

    public Cart toCart(CartDTO cartDTO){
        User user = userRepo.findById(cartDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", cartDTO.getUserId(), "User-ID"));

        Cart cart=new Cart();
        cart.setCartId(cartDTO.getCartId());


        List<CartItem> cartItems = cartDTO.getCartItems().stream()
                .map(orderItemDTO -> {
                    CartItem cartItem = cartItemMapping.toCartItem(orderItemDTO);
                    cartItem.setCart(cart);
                    return cartItem;
                })
                .collect(Collectors.toList());
        cart.setCartItems(cartItems);
        cart.setUser(user);

        return cart;
    }

    public CartDTO toCartDTO(Cart cart){
        CartDTO cartDTO=new CartDTO();

        cartDTO.setCartId(cart.getCartId());
        cartDTO.setCreatedAt(cart.getCreatedAt());
        cartDTO.setUserId(cart.getUser().getUserId());
        cartDTO.setUsername(cart.getUser().getUsername());

        cartDTO.setCartItems(cart.getCartItems().stream().map(cartItemMapping::toCartItemDTO).collect(Collectors.toList()));
        return cartDTO;
    }
}
