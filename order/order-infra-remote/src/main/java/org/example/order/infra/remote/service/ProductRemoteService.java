package org.example.order.infra.remote.service;

import jakarta.annotation.Resource;
import org.example.order.domain.model.product.Product;
import org.example.order.infra.remote.client.ProductApiClient;
import org.example.order.infra.remote.client.response.BaseRemoteResponse;

import java.util.Collections;
import java.util.List;

/**
 * @program: ddd-demo-order
 * @description:
 * @author: czqu
 **/
public class ProductRemoteService {
    @Resource
    ProductApiClient productApiClient;

    @Override
    public List<Product> getProductInfos(List<Long> productIds) {
        BaseRemoteResponse<List<Product>> response = productApiClient.getProductInfos(productIds);

        if (response == null || response.failed()) {
            log.error("getProductInfos error,request:{},response:{}", productIds, response);
            return Collections.emptyList();
        }

        return response.getData();
    }
}
