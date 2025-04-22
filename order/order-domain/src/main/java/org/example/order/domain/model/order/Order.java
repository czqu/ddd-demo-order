package org.example.order.domain.model.order;

/**
 * @program: ddd-demo-order
 * @description:
 * @author: czqu
 **/
public class Order {
    /**
     * 创建订单
     * @param orderNo
     * @param deliveryAddress
     * @param orderItemList
     * @param userId
     * @return
     */
    public static Order create(String orderNo, DeliveryAddress deliveryAddress, List<OrderItem> orderItemList, Long userId) {
        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setDeliveryAddress(deliveryAddress);
        order.setOrderItemList(orderItemList);
        order.setUserId(userId);
        order.initialize();
        return order;
    }
    //订单状态初始化
    public void initialize() {
        this.setStatus(OrderStateEnum.INIT.getValue());
        this.setOrderTime(new Date());
        calculateTotalPrice();
    }

    public void setTotalPrice(Long totalPrice) {
        if (Objects.isNull(totalPrice) || totalPrice < 0)
            throw new DemoBusinessException("Illegal totalPrice");
        this.totalPrice = totalPrice;
    }

    public void setOrderNo(String orderNo) {
        if (StringUtils.isBlank(orderNo))
            throw new DemoBusinessException("orderNo can not be null");
        this.orderNo = orderNo;
    }
}
