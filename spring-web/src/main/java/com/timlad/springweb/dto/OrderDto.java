package com.timlad.springweb.dto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private Integer totalPrice;
    private String address;
    private String phone;
    private String userName;
    private List<OrderItemDto> items;
}
