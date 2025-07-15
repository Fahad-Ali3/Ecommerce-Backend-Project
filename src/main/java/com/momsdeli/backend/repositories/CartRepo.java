package com.momsdeli.backend.repositories;

import com.momsdeli.backend.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart,Long> {
}
