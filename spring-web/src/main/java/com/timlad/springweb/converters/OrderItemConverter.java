package com.timlad.springweb.converters;

import com.timlad.springweb.dto.OrderItemDto;
import com.timlad.springweb.dto.ProductDto;
import com.timlad.springweb.entities.Order;
import com.timlad.springweb.entities.OrderItem;
import com.timlad.springweb.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class OrderItemConverter {

    public OrderItem dtoToEntity(OrderItemDto orderItemDto) {
       throw new UnsupportedOperationException();
    }

    public OrderItemDto entityToDto(OrderItem orderItem) {
        return new OrderItemDto(orderItem.getProduct().getId(), orderItem.getProduct().getTitle(),
                orderItem.getQuantity(), orderItem.getPricePerProduct(),
                orderItem.getPrice());
    }
}
