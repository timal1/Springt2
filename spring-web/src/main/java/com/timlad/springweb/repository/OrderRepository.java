package com.timlad.springweb.repository;

import com.timlad.springweb.entities.Order;
import com.timlad.springweb.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Product> {

    Order getByUserId(Long id);
}
