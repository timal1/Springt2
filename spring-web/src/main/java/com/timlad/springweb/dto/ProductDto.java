package com.timlad.springweb.dto;

import com.timlad.springweb.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String title;
    private double price;
    private Integer amount;

    public ProductDto(Long id, String title, double price) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.amount = 1;
    }
}
