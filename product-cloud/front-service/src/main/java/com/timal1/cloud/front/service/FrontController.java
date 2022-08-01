package com.timal1.cloud.front.service;

import com.timal1.cloud.common.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class FrontController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping()
    public List<ProductDto> getProductsFromProductService() {
        List<ProductDto> data = restTemplate.getForObject("http://product-service/api/v1/products", List.class);
        return data;
    }
}
