package org.example.order.infra.repository.convertor;

import org.example.order.domain.model.order.OrderItem;
import org.example.order.infra.repository.entity.OrderItemEntity;

/**
 * @program: ddd-demo-order
 * @description:
 * @author: czqu
 **/
@Mapper
public interface OrderItemConvertor {
    OrderItemConvertor INSTANCE = Mappers.getMapper(OrderItemConvertor.class);

    OrderItemEntity convertToEntity(OrderItem domain);
    OrderItem convertToDomain(OrderItemEntity entity);
}