package org.example.order.domain.model.order;

/**
 * @program: ddd-demo-order
 * @description:
 * @author: czqu
 **/

import lombok.Data;

/**
 * 订单项领域模型
 */
@Data
public class OrderItem {
    private Long id;
    private Long orderId;
    private Long productId;
    private String productName;
    private Integer count;
    private Long price;
    // 业务方法（如计算小计）
    public Long calculateSubtotal() {
        return price * count;
    }
}