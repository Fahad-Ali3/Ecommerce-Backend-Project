package com.momsdeli.backend.service;

import com.momsdeli.backend.dto.PaymentRequestDTO;
import com.momsdeli.backend.dto.PaymentResponseDTO;
import org.springframework.stereotype.Service;


public interface PaymentService {

    public PaymentResponseDTO processPayment(PaymentRequestDTO paymentRequestDTO);

    PaymentResponseDTO getPaymentByOrder(Long orderId);
}
