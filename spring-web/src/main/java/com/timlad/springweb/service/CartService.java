package com.timlad.springweb.service;

import com.timlad.springweb.dto.Cart;
import com.timlad.springweb.entities.Product;
import com.timlad.springweb.exeptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;

    private Cart cart;

    @PostConstruct
    public void init() {
        cart = new Cart();
    }

    public Cart getCurrentCart() {
        return cart;
    }

    public void addProductByIdToCart(Long productId) {
        if(!getCurrentCart().addProduct(productId)) {
            Product product = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Невозможно добавить продукт в корзину, продукт не найден, id" + productId));
            getCurrentCart().addProduct(product);
        }
    }

    public void clear() {
        getCurrentCart().clear();
    }

}
