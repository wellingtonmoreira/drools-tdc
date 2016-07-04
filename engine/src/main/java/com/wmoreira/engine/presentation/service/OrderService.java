package com.wmoreira.engine.presentation.service;

import br.com.wmoreira.domains.Order;
import br.com.wmoreira.domains.dto.OrderDTO;
import br.com.wmoreira.domains.mapper.OrderMapper;
import com.wmoreira.engine.component.OrderComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderService {

    private OrderMapper orderMapper;
    private OrderComponent orderComponent;

    @Autowired
    public OrderService(OrderMapper orderMapper, OrderComponent orderComponent) {
        this.orderMapper = orderMapper;
        this.orderComponent = orderComponent;
    }

    @RequestMapping(value = "/orders/price",
                    method = RequestMethod.POST,
                    produces = "application/json")
    public ResponseEntity<OrderDTO[]> price(@RequestBody OrderDTO[] orderDTOs) {
        Order[] products = orderMapper.mapManyFromLeftObject(orderDTOs);
        return ResponseEntity.ok().body(orderMapper.mapManyFromRightObject(orderComponent.priceOrders(products)));
    }
}
