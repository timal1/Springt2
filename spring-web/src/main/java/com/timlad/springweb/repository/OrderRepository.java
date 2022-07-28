package com.timlad.springweb.repository;

import com.timlad.springweb.entities.Order;
import com.timlad.springweb.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.user.username = ?1")
    List<Order> findAllByUsername(String username);
}
