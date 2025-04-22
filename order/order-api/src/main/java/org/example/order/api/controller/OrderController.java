package org.example.order.api.controller;


import jakarta.annotation.Resource;
import org.example.order.application.pojo.cmd.OrderCreateCmd;
import org.example.order.application.pojo.response.OrderResponse;
import org.example.order.application.service.OrderService;
import org.example.order.infra.repository.convertor.OrderConvertor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    OrderService orderService;


    @PostMapping(name = "/create")
    public OrderResponse create(OrderCreateCmd cmd) {
        return OrderConvertor.convertToResponse(orderService.createOrder(cmd));
    }
}
