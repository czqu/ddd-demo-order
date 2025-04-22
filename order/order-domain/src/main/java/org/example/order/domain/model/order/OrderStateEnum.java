package org.example.order.domain.model.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
* @program: ddd-demo-order
*
* @description:
*
* @author: czqu
*
*
**/
@Getter
@AllArgsConstructor
public enum OrderStateEnum {
    INIT(1, "初始化"),
    PAID(2, "已支付"),
    SHIPPED(3, "已发货"),
    COMPLETED(4, "已完成"),
    CANCELED(5, "已取消");

    private final Integer value;
    private final String description;
}