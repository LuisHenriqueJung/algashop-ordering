package com.jung.algashop.ordering.domain.entity;

import com.jung.algashop.ordering.domain.exception.OrderStatusCannotBeChangedException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderCancelTest {

    @Test
    public void givenDraftOrder_whenCancel_shouldChangeStatusAndSetCancelledAt() {
        Order order = OrderTestDataBuilder.anOrder().status(OrderStatus.DRAFT).build();

        order.cancel();

        Assertions.assertThat(order.isCanceled()).isTrue();
        Assertions.assertThat(order.cancelledAt()).isNotNull();
    }

    @Test
    public void givenPlacedOrder_whenCancel_shouldChangeStatusAndSetCancelledAt() {
        Order order = OrderTestDataBuilder.anOrder().status(OrderStatus.PLACED).build();

        order.cancel();

        Assertions.assertThat(order.isCanceled()).isTrue();
        Assertions.assertThat(order.cancelledAt()).isNotNull();
    }

    @Test
    public void givenPaidOrder_whenCancel_shouldChangeStatusAndSetCancelledAt() {
        Order order = OrderTestDataBuilder.anOrder().status(OrderStatus.PAID).build();

        order.cancel();

        Assertions.assertThat(order.isCanceled()).isTrue();
        Assertions.assertThat(order.cancelledAt()).isNotNull();
    }

    @Test
    public void givenReadyOrder_whenCancel_shouldChangeStatusAndSetCancelledAt() {
        Order order = OrderTestDataBuilder.anOrder().status(OrderStatus.READY).build();

        order.cancel();

        Assertions.assertThat(order.isCanceled()).isTrue();
        Assertions.assertThat(order.cancelledAt()).isNotNull();
    }

    @Test
    public void givenCanceledOrder_whenCancel_shouldThrowException() {
        Order order = OrderTestDataBuilder.anOrder().status(OrderStatus.CANCELED).build();

        Assertions.assertThatExceptionOfType(OrderStatusCannotBeChangedException.class)
                .isThrownBy(order::cancel);

        Assertions.assertThat(order.isCanceled()).isTrue();
    }
}
