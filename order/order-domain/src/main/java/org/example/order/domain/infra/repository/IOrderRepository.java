package org.example.order.domain.infra.repository;

import org.example.order.domain.model.order.Order;

/**
 * @program: ddd-demo-order
 * @description:
 * @author: czqu
 **/
public abstract class IOrderRepository {
    public abstract void createOrder(Order order);
}
