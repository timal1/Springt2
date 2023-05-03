package com.timlad.springweb.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String title;
    private double price;
    private Integer amount;

    private String category;

    public ProductDto(Long id, String title, double price, String category) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.amount = 1;
        this.category = category;
    }
}
