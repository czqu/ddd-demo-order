package org.example.order.domain.model.order;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * @program: ddd-demo-order
 * @description:
 * @author: czqu
 **/
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // 显式指定比较字段
public class DeliveryAddress {

    /** 收货人 */
    @EqualsAndHashCode.Include // 参与相等性比较
    private final String consignee;

    /** 省 */
    @EqualsAndHashCode.Include
    private final String province;

    /** 市 */
    @EqualsAndHashCode.Include
    private final String city;

    /** 区/县 */
    @EqualsAndHashCode.Include
    private final String district;

    /** 详细地址（街道/门牌号等） */
    @EqualsAndHashCode.Include
    private final String detailAddress;

    /** 联系电话 */
    @EqualsAndHashCode.Include
    private final String phone;

    /**
     * 工厂方法：创建配送地址
     * @param consignee 收货人
     * @param province 省
     * @param city 市
     * @param district 区/县
     * @param detailAddress 详细地址
     * @param phone 联系电话
     * @return DeliveryAddress 实例
     */
    public static DeliveryAddress of(
            String consignee,
            String province,
            String city,
            String district,
            String detailAddress,
            String phone
    ) {
        // 可添加业务校验（如电话格式、地址非空等）
        return new DeliveryAddress(consignee, province, city, district, detailAddress, phone);
    }


}