package com.jung.algashop.ordering.domain.entity;

import com.jung.algashop.ordering.domain.model.entity.OrderItem;
import com.jung.algashop.ordering.domain.model.valueobject.Quantity;
import com.jung.algashop.ordering.domain.model.valueobject.id.OrderId;
import org.junit.jupiter.api.Test;

class OrderItemTest {

    @Test
    public void shouldGenerate() {
        OrderItem.brandNew()
                .product(ProductTestDataBuilder.aProduct().build())
                .quantity(new Quantity(1))
                .orderId(new OrderId())
                .build();
    }

}