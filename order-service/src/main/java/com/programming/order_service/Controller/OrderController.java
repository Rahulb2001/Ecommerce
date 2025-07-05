package com.programming.order_service.Controller;


import com.programming.order_service.DTO.OrderRequest;
import com.programming.order_service.Service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        if(orderService.placeOrder(orderRequest)){
            return "Order placed successfully!";
        }else{
            return "Order could not be placed due to insufficient stock.";
        }
    }
}
