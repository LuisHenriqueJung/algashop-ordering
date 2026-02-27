package com.jung.algashop.ordering.domain.entity;

import com.jung.algashop.ordering.domain.exception.OrderCannotBeEditedException;
import com.jung.algashop.ordering.domain.exception.OrderDoesNotContainOrderItemException;
import com.jung.algashop.ordering.domain.valueobject.Money;
import com.jung.algashop.ordering.domain.valueobject.Quantity;
import com.jung.algashop.ordering.domain.valueobject.id.CustomerId;
import com.jung.algashop.ordering.domain.valueobject.id.OrderItemId;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderRemoveItemTest {

    @Test
    public void givenDraftOrderWithItems_whenRemoveItem_shouldRemoveAndRecalculate() {
        Order order = Order.draft(new CustomerId());
        
        order.addItem(ProductTestDataBuilder.aProductAltMousePad().build(), new Quantity(2));
        order.addItem(ProductTestDataBuilder.aProductAltRamMemory().build(), new Quantity(1));
        
        Assertions.assertThat(order.items()).hasSize(2);
        Assertions.assertThat(order.totalAmount()).isEqualTo(new Money("400"));
        Assertions.assertThat(order.totalItems()).isEqualTo(new Quantity(3));
        
        OrderItem itemToRemove = order.items().stream()
                .filter(i -> i.productName().name().equals("Mouse Pad"))
                .findFirst()
                .get();
                
        order.removeItem(itemToRemove.id());
        
        Assertions.assertThat(order.items()).hasSize(1);
        Assertions.assertThat(order.totalAmount()).isEqualTo(new Money("200"));
        Assertions.assertThat(order.totalItems()).isEqualTo(new Quantity(1));
    }

    @Test
    public void givenDraftOrder_whenRemoveNonExistentItem_shouldThrowException() {
        Order order = Order.draft(new CustomerId());
        order.addItem(ProductTestDataBuilder.aProductAltMousePad().build(), new Quantity(2));
        
        Assertions.assertThatExceptionOfType(OrderDoesNotContainOrderItemException.class)
                .isThrownBy(() -> order.removeItem(new OrderItemId()));
    }

    @Test
    public void givenPlacedOrder_whenRemoveItem_shouldThrowException() {
        Order order = OrderTestDataBuilder.anOrder().status(OrderStatus.PLACED).build();
        OrderItemId firstItemId = order.items().iterator().next().id();
        
        Assertions.assertThatExceptionOfType(OrderCannotBeEditedException.class)
                .isThrownBy(() -> order.removeItem(firstItemId));
    }
}
