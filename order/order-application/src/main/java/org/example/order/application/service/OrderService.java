package org.example.order.application.service;

import jakarta.annotation.Resource;
import org.example.order.application.pojo.cmd.OrderCreateCmd;
import org.example.order.domain.infra.repository.IOrderRepository;
import org.example.order.domain.infra.repository.IUserRepository;
import org.example.order.domain.model.order.OrderItem;
import org.example.order.domain.model.user.User;
import org.example.order.domain.service.OrderDomainService;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @program: ddd-demo-order
 * @description:
 * @author: czqu
 **/
public class OrderService {
    @Resource
    private IUserRepository userRepository;
    @Resource
    private IOrderRepository orderRepository;

    @Resource
    private OrderDomainService orderDomainService;

    @Transactional(rollbackFor = Exception.class)
    public Order createOrder(OrderCreateCmd cmd) {
        String orderNo = UUID.randomUUID().toString();
        //从资源库获取用户
        Optional<User> userOptional = userRepository.findById(cmd.getUserId());
        User user = userOptional.orElseThrow(() -> new DemoBusinessException("用户不存在"));

        //把参数转换为领域模型,可以定义convertor来进行转换
        List<OrderItem> orderItemList = makeOrderItems(cmd.getProductItems(), orderNo);
        //商品域-检查库存，product和OrderItem属于两个域,且使用了外部product服务,所以使用领域服务
        orderDomainService.checkInventoryAndAssembleOrderItems(orderItemList);

        //配送地址
        Optional<DeliveryAddress> deliveryInfoOptional = deliveryAddressRepository.findById(cmd.getDeliveryAddressId());
        DeliveryAddress deliveryAddress = deliveryInfoOptional.orElseThrow(() -> new DemoBusinessException("配送信息不存在"));

        //创建订单,最好的方式是使用工厂
        Order order = Order.create(orderNo, deliveryAddress, orderItemList, user.getUserId());
        //调度域-锁定库存,用到了远程服务,所以放到了领域服务
        orderDomainService.lockInventory(order);

        //创建订单
        orderRepository.createOrder(order);
        //发布订单创建事件
        orderMessageProducer.publish(order, OrderEventTypeEnum.INIT);
        //返回领域模型,由用户界面层决定转为什么样的模型, 从架构层面限制模型滥用和模型滥转
        return order;
    }
}
