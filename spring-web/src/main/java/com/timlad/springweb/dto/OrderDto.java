package com.timlad.springweb.dto;

import com.timlad.springweb.entities.OrderItem;
import com.timlad.springweb.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderDto {

    private int totalPrice;
    private String address;
    private String phone;
    private String userName;
    private List<OrderItem> orderItems;

}
