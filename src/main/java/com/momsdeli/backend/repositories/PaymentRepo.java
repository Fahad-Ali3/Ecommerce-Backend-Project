package com.momsdeli.backend.repositories;

import com.momsdeli.backend.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepo extends JpaRepository<Payment,Long> {

    Optional<Payment> findByOrder_OrderId(Long orderId);
}
