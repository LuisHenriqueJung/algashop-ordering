package com.jung.algashop.ordering.domain.entity;

import com.jung.algashop.ordering.domain.exception.OrderStatusCannotBeChangedException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderMarkAsReadyTest {

    @Test
    public void givenPaidOrder_whenMarkAsReady_shouldChangeStatusAndSetReadyAt() {
        Order order = OrderTestDataBuilder.anOrder().status(OrderStatus.PAID).build();

        order.markAsReady();

        Assertions.assertThat(order.status()).isEqualTo(OrderStatus.READY);
        Assertions.assertThat(order.readyAt()).isNotNull();
    }

    @Test
    public void givenDraftOrder_whenMarkAsReady_shouldThrowException() {
        Order order = OrderTestDataBuilder.anOrder().status(OrderStatus.DRAFT).build();

        Assertions.assertThatExceptionOfType(OrderStatusCannotBeChangedException.class)
                .isThrownBy(order::markAsReady);

        Assertions.assertThat(order.status()).isEqualTo(OrderStatus.DRAFT);
        Assertions.assertThat(order.readyAt()).isNull();
    }

    @Test
    public void givenPlacedOrder_whenMarkAsReady_shouldThrowException() {
        Order order = OrderTestDataBuilder.anOrder().status(OrderStatus.PLACED).build();

        Assertions.assertThatExceptionOfType(OrderStatusCannotBeChangedException.class)
                .isThrownBy(order::markAsReady);

        Assertions.assertThat(order.status()).isEqualTo(OrderStatus.PLACED);
        Assertions.assertThat(order.readyAt()).isNull();
    }
}
