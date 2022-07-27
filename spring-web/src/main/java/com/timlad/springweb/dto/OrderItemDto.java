package com.timlad.springweb.dto;

import com.timlad.springweb.entities.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemDto {
    private Long productId;
    private String productTitle;
    private int quantity;
    private double pricePerProduct;
    private double price;

    public OrderItemDto(Product product) {
        this.productId = product.getId();
        this.productTitle = product.getTitle();
        this.quantity = 1;
        this.pricePerProduct = product.getPrice();
        this.price = product.getPrice();
    }

    public void changeQuantity(double delta) {
        this.quantity += delta;
        this.price = this.quantity * this.pricePerProduct;
    }
}
