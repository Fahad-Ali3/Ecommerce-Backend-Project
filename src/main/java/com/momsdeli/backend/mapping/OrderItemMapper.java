package com.momsdeli.backend.mapping;

import com.momsdeli.backend.dto.OrderItemDTO;
import com.momsdeli.backend.exceptions.ResourceNotFoundException;
import com.momsdeli.backend.model.Order;
import com.momsdeli.backend.model.OrderItem;
import com.momsdeli.backend.model.Product;
import com.momsdeli.backend.repositories.OrderRepo;
import com.momsdeli.backend.repositories.ProductRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ProductRepo productRepo;

    // Convert OrderItem entity to OrderItemDTO
    public OrderItemDTO toOrderItemDTO(OrderItem orderItem) {
        OrderItemDTO item=new OrderItemDTO();

        if(orderItem.getOrder().getOrderId()!=null) {
            item.setOrderId(orderItem.getOrder().getOrderId());
        }

        item.setOrderItemId(orderItem.getOrderItemId());
        item.setQuantity(orderItem.getQuantity());
        item.setPrice(orderItem.getPrice());
        item.setProductName(orderItem.getProduct().getProductName());
        item.setProductId(orderItem.getProduct().getProductId());
        return item;
    }

    // Convert OrderItemDTO to OrderItem entity
    // Convert OrderItemDTO to OrderItem entity
    public OrderItem toOrderItemEntity(OrderItemDTO orderItemDTO) {
        Product product = productRepo.findById(orderItemDTO.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product", orderItemDTO.getProductId(), "Product-ID"));

        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setQuantity(orderItemDTO.getQuantity());
        orderItem.setPrice(orderItemDTO.getPrice());
        orderItem.setOrderItemId(orderItemDTO.getOrderItemId());

        return orderItem;
    }

}
