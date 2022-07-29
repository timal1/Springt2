package com.timlad.springweb.service;

import com.timlad.springweb.dto.Cart;
import com.timlad.springweb.dto.OrderDetailsDto;
import com.timlad.springweb.entities.Order;
import com.timlad.springweb.entities.OrderItem;
import com.timlad.springweb.entities.User;
import com.timlad.springweb.exeptions.ResourceNotFoundException;
import com.timlad.springweb.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartService cartService;
    private final OrderRepository orderRepository;
    private final ProductService productService;

    @Transactional
    public void createOrder(User user, OrderDetailsDto orderDetailsDto) {
        String cartKey = cartService.getCartUuidFromSuffix(user.getUsername());
        Cart currentCart = cartService.getCurrentCart(cartKey);
        Order order = new Order();
        order.setAddress(orderDetailsDto.getAddress());
        order.setPhone(orderDetailsDto.getPhone());
        order.setUser(user);
        order.setTotalPrice(currentCart.getTotalPrice());
        List<OrderItem> items = currentCart.getItems().stream()
                .map(o -> {
                    OrderItem item = new OrderItem();
                    item.setProduct(productService.findById(o.getProductId())
                            .orElseThrow(() -> new ResourceNotFoundException("Not create order, the product is not found in the database, id: "
                                    + o.getProductId())));
                    item.setOrder(order);
                    item.setQuantity(o.getQuantity());
                    item.setPricePerProduct(o.getPricePerProduct());
                    item.setPrice(o.getPrice());
                   return item;
                }).collect(Collectors.toList());
        order.setItems(items);
        orderRepository.save(order);
        cartService.clearCart(cartKey);
    }

    public List<Order> findOrdersByUserName(String username) {
        return orderRepository.findAllByUsername(username);
    }
}
