package com.timlad.springweb.service;

import com.timlad.springweb.dto.Cart;
import com.timlad.springweb.dto.OrderItemDto;
import com.timlad.springweb.entities.Order;
import com.timlad.springweb.entities.OrderItem;
import com.timlad.springweb.entities.Product;
import com.timlad.springweb.entities.User;
import com.timlad.springweb.exeptions.ResourceNotFoundException;
import com.timlad.springweb.repository.OrderItemRepository;
import com.timlad.springweb.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final CartService cartService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;


    public void addOrderItems(User user, Cart cart, Long orderId) {

        Order order = orderRepository.getById(orderId);


        for (OrderItemDto oid: cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            Product product = productService.findById(oid.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Not add order, the product is not found in the database, id: "
                            + oid.getProductId()));
            orderItem.setProduct(product);
            orderItem.setUser(user);
            orderItem.setOrder(order);
            orderItem.setQuantity(oid.getQuantity());
            orderItem.setPricePerProduct(oid.getPricePerProduct());
            orderItem.setPrice(oid.getPrice());
            orderItemRepository.save(orderItem);
        }
    }
}
