package com.momsdeli.backend.service;

import com.momsdeli.backend.dto.OrderItemDTO;
import com.momsdeli.backend.model.OrderItem;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderItemService {

    OrderItemDTO deleteOrderItem(Long orderItemId);
    OrderItemDTO getOrderItemById(Long orderItemId);
    Page<OrderItemDTO> getAllOrderItems(int page, int size, String sortBy);
    public OrderItemDTO createOrderItem(OrderItemDTO orderItemDTO);
}
