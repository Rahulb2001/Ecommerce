package com.programming.order_service.Repository;

import com.programming.order_service.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // This interface will automatically provide CRUD operations for the Order entity.
    // Additional custom query methods can be defined here if needed.
}
