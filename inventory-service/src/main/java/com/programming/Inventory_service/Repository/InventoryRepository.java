package com.programming.Inventory_service.Repository;

import com.programming.Inventory_service.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findBySkuCode(String skuCode);

    List<Inventory> findBySkuCodeIn(List<String> skuCode);

    // This interface will automatically provide CRUD operations for the Inventory entity.
    // Additional custom query methods can be defined here if needed.
}
