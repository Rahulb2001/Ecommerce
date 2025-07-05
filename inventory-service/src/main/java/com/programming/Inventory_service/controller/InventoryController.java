package com.programming.Inventory_service.controller;


import com.programming.Inventory_service.DTO.InventoryResponse;
import com.programming.Inventory_service.Service.InventoryService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@AllArgsConstructor
@Data
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public List<InventoryResponse> IsInStock(@RequestParam List<String> skuCode) {
        return inventoryService.isInStock(skuCode);
    }
}
