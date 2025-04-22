package org.example.order.infra.repository.repository;

import jakarta.annotation.Resource;
import org.example.order.domain.infra.repository.IOrderRepository;
import org.example.order.domain.model.order.Order;
import org.example.order.infra.repository.entity.OrderEntity;
import org.example.order.infra.repository.repository.mapper.OrderItemMapper;
import org.example.order.infra.repository.repository.mapper.OrderMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @program: ddd-demo-order
 * @description:
 * @author: czqu
 **/

@Repository
public class OrderRepository implements IOrderRepository {
    @Resource
    OrderMapper orderMapper;

    @Resource
    OrderItemMapper orderItemMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private ElasticsearchTemplate<OrderEntityES, Long> orderESTemplate;

    public Optional<Order> findById(long orderId) {
        return Optional.ofNullable(OrderConvertor.convertToDO(elasticsearchTemplate.getById(orderId,OrderEntity.class)));
    }

    /**
     * 创建订单
     * @param order
     */
    @Override
    public void createOrder(Order order) {
        //保存订单
        OrderEntity orderEntity = OrderConvertor.convertToEntity(order);
        orderMapper.insert(orderEntity);
        order.setOrderId(orderEntity.getId());
        //保存订单项
        List<OrderItem> orderItemList = order.getOrderItemList();
        orderItemList.forEach(orderItem -> {
            orderItem.setOrderId(orderEntity.getId());
            orderItemMapper.insert(OrderItemConvertor.INSTANCT.convertToEntity(orderItem));
        });
    }
}