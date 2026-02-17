package com.algaworks.algashop.ordering.domain.entity;

import com.algaworks.algashop.ordering.domain.value_object.Money;
import com.algaworks.algashop.ordering.domain.value_object.ProductName;
import com.algaworks.algashop.ordering.domain.value_object.Quantity;
import com.algaworks.algashop.ordering.domain.value_object.id.CustomerId;
import com.algaworks.algashop.ordering.domain.value_object.id.ProductId;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class OrderTest {

    @Test
    void shouldCreateOrder() {
        Order order = Order.draft(new CustomerId());
    }

    @Test
    void shouldAddItem() {
        Order order = Order.draft(new CustomerId());
        ProductId productId = new ProductId();
        order.addItem(productId, new ProductName("Mouse Pad"), new Money("100"), new Quantity(1));
        Assertions.assertThat(order.items().size()).isEqualTo(1);

        OrderItem item = order.items().iterator().next();
        Assertions.assertWith(item,
                (i) -> Assertions.assertThat(i.productName()).isEqualTo(new ProductName("Mouse Pad")),
                (i) -> Assertions.assertThat(i.quantity()).isEqualTo(new Quantity(1)),
                (i) -> Assertions.assertThat(i.price()).isEqualTo(new Money("100")),
                (i) -> Assertions.assertThat(i.productId()).isEqualTo(productId),
                (i) -> Assertions.assertThat(i.id()).isNotNull(),
                (i) -> Assertions.assertThat(i.orderId()).isNotNull()
        );
    }
}