package com.programming.order_service.Service;


import com.programming.order_service.DTO.InventoryResponse;
import com.programming.order_service.DTO.OrderRequest;
import com.programming.order_service.Model.Order;
import com.programming.order_service.Model.OrderLineitems;
import com.programming.order_service.Repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public boolean placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        log.info("Placing order with order number: {}", order.getOrderNumber());



        order.setOrderLineitems(orderRequest.getOrderLineitemsdto().stream()
                .map(dto -> new OrderLineitems(dto.getId(), dto.getSkuCode(), dto.getPrice(), dto.getQuantity()))
                .collect(Collectors.toList()));



        List<String> skuCodes = order.getOrderLineitems().stream()
                .map(OrderLineitems::getSkuCode)
                .collect(Collectors.toList());

        log.info("Extracted SKU codes from order: {}", skuCodes.toString());

        try {
            // Call Inventory Service

            log.info("Calling Inventory Service to check stock for SKU codes: {}", skuCodes);

            InventoryResponse[] inventoryResponsesArray = webClientBuilder.build()
                    .get()
                    .uri("http://inventory-service/api/inventory",
                            uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                    .retrieve()
                    .bodyToMono(InventoryResponse[].class)
                    .block();

            log.info("Received inventory responses from Inventory Service");

            log.info("Inventory Responses: {}", Arrays.toString(inventoryResponsesArray));

            // Check if all products are in stock
            boolean allProductsInStock = Arrays.stream(inventoryResponsesArray)
                    .allMatch(InventoryResponse::isInStock);

            if (allProductsInStock) {
                log.info("All products are in stock, proceeding to place order");

                orderRepository.save(order);

                log.info("Order placed successfully with order number: {}", order.getOrderNumber());
                return true;
            } else {
                log.error("Product is not in stock");
                throw new IllegalArgumentException("Product is not in stock");
            }
        } catch (Exception e) {
            log.error("Error occurred while placing the order: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to place order", e);

        }

    }


    //call inventory service stock


}
