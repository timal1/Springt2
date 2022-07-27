package com.timlad.springweb.service;

import com.timlad.springweb.dto.Cart;
import com.timlad.springweb.entities.Order;
import com.timlad.springweb.entities.User;
import com.timlad.springweb.exeptions.ResourceNotFoundException;
import com.timlad.springweb.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartService cartService;
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;

//    public List<Order> findAll() {
//        return orderRepository.findAll();
//    }

    public void addOrder(Principal principal) {
        Cart currentCart = cartService.getCurrentCart();
        if (currentCart == null) {
            return;
        }
        User user = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException("Такой пользователь не зарегистрирован, имя: " + principal.getName()));

        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(currentCart.getTotalPrice());

        Long orderId = orderRepository.save(order).getId();
        orderItemService.addOrderItems(user, currentCart, orderId);


    }
}
