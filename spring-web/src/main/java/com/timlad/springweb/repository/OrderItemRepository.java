package com.timlad.springweb.repository;

import com.timlad.springweb.entities.Order;
import com.timlad.springweb.entities.OrderItem;
import com.timlad.springweb.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>, JpaSpecificationExecutor<Product> {

}
