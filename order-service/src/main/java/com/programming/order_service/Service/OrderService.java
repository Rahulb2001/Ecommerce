package com.programming.order_service.Service;


import com.programming.order_service.DTO.OrderRequest;
import com.programming.order_service.Model.Order;
import com.programming.order_service.Model.OrderLineitems;
import com.programming.order_service.Repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Data
@AllArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;


    public void placeOrder(OrderRequest orderRequest) {
       Order order = new Order();
       order.setOrderNumber(UUID.randomUUID().toString());

         // Convert OrderLineitemsDTO to OrderLineitems

         order.setOrderLineitems(orderRequest.getOrderLineitemsdto().stream()
                .map(dto -> new OrderLineitems(dto.getId(), dto.getSkuCode(), dto.getPrice(), dto.getQuantity()))
                .toList());

         orderRepository.save(order);
            log.info("Order placed successfully with order number: {}", order.getOrderNumber());


        System.out.println("Order placed successfully with details: " + orderRequest);
    }


}
