package org.example.order.application.pojo.cmd;

/**
 * @program: ddd-demo-order
 * @description:
 * @author: czqu
 **/

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.order.application.pojo.cmd.co.OrderCreateProductItemCO;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 创建订单命令
 */
@Data
public class OrderCreateCmd {
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @NotNull(message = "配送地址ID不能为空")
    private Long deliveryAddressId;

    @NotNull(message = "订单项列表不能为空")
    private List<OrderCreateProductItemCO> productItems;
}