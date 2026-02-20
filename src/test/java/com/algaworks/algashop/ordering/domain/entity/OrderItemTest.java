package com.algaworks.algashop.ordering.domain.entity;

import com.algaworks.algashop.ordering.domain.value_object.Money;
import com.algaworks.algashop.ordering.domain.value_object.ProductName;
import com.algaworks.algashop.ordering.domain.value_object.Quantity;
import com.algaworks.algashop.ordering.domain.value_object.id.OrderId;
import com.algaworks.algashop.ordering.domain.value_object.id.ProductId;
import org.junit.jupiter.api.Test;

class OrderItemTest {

    @Test
    void shouldCreateOrderItem() {
        OrderItem orderItem = OrderItem.brandNew()
                .orderId(new OrderId())
                .productId(new ProductId())
                .productName(new ProductName("Mouse "))
                .price(new Money())
                .quantity(new Quantity(1))
                .build();
    }

}