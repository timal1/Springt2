package com.timlad.springweb.controller;

import com.timlad.springweb.converters.ProductConverter;
import com.timlad.springweb.dto.Cart;
import com.timlad.springweb.dto.ProductDto;
import com.timlad.springweb.entities.Product;
import com.timlad.springweb.exeptions.ResourceNotFoundException;
import com.timlad.springweb.service.CartService;
import com.timlad.springweb.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping
    public Cart getCurrentCart() {
        return cartService.getCurrentCart();
    }

    @GetMapping("/add/{id}")
    public void addProduct(@PathVariable Long id) {
        cartService.addProductByIdToCart(id);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cartService.getCurrentCart().clear();
    }
}
