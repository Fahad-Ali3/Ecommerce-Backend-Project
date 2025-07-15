package com.momsdeli.backend.service.impl;

import com.momsdeli.backend.dto.OrderItemDTO;
import com.momsdeli.backend.exceptions.ResourceNotFoundException;
import com.momsdeli.backend.mapping.OrderItemMapper;
import com.momsdeli.backend.mapping.OrderMapper;
import com.momsdeli.backend.model.Order;
import com.momsdeli.backend.model.OrderItem;
import com.momsdeli.backend.repositories.OrderItemRepo;
import com.momsdeli.backend.repositories.OrderRepo;
import com.momsdeli.backend.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderItemRepo orderItemRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public OrderItemDTO createOrderItem(OrderItemDTO orderItemDTO) {
        // Convert DTO to entity
        OrderItem orderItem = orderItemMapper.toOrderItemEntity(orderItemDTO);

        // Save the entity
        OrderItem savedOrderItem = orderItemRepo.save(orderItem);
        Order order=orderItem.getOrder();
        order.getOrderItems().add(savedOrderItem);
        order=orderRepo.save(order);
        // Convert the saved entity back to DTO
        return orderItemMapper.toOrderItemDTO(savedOrderItem);
    }
    @Override
    public OrderItemDTO deleteOrderItem(Long orderItemId) {
        OrderItem orderItem=orderItemRepo.findById(orderItemId).orElseThrow(()->new ResourceNotFoundException("Order Item",orderItemId,"OrderItem-ID"));
        orderItemRepo.delete(orderItem);
        return orderItemMapper.toOrderItemDTO(orderItem);
    }

    @Override
    public OrderItemDTO getOrderItemById(Long orderItemId) {
        OrderItem orderItem=orderItemRepo.findById(orderItemId).orElseThrow(()->new ResourceNotFoundException("Order Item",orderItemId,"OrderItem-ID"));

        return orderItemMapper.toOrderItemDTO(orderItem);
    }
    @Override
    public Page<OrderItemDTO> getAllOrderItems(int page, int size, String sortBy) {
        // Create a Pageable instance with sorting and pagination
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());

        // Get the paginated and sorted order items from the repository
        Page<OrderItem> orderItemsPage = orderItemRepo.findAll(pageable);

        // Convert the order items to DTOs and return the Page
        return orderItemsPage.map(orderItemMapper::toOrderItemDTO);
    }

}
