package com.programming.order_service.DTO;


import com.programming.order_service.Model.OrderLineitems;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private List<OrderLineitemsDTO> orderLineitemsdto;
}
