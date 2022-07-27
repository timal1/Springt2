package com.timlad.springweb.controller;

import com.timlad.springweb.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/add")
    public void addOrder(Principal principal) {
         orderService.addOrder(principal);
    }
}
