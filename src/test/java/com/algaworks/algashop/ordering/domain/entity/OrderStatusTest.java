package com.algaworks.algashop.ordering.domain.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderStatusTest {

    @Test
    void canChangeTo(){
        Assertions.assertTrue(OrderStatus.DRAFT.canChangeTo(OrderStatus.PLACED));
        Assertions.assertTrue(OrderStatus.PLACED.canChangeTo(OrderStatus.PAID));
        Assertions.assertTrue(OrderStatus.PAID.canChangeTo(OrderStatus.READY));
        Assertions.assertTrue(OrderStatus.READY.canChangeTo(OrderStatus.CANCELED));
        Assertions.assertFalse(OrderStatus.PLACED.canChangeTo(OrderStatus.DRAFT));
    }

    @Test
    void canNotChangeTo(){
        Assertions.assertFalse(OrderStatus.PLACED.canChangeTo(OrderStatus.DRAFT));
        Assertions.assertFalse(OrderStatus.PAID.canChangeTo(OrderStatus.DRAFT));
        Assertions.assertFalse(OrderStatus.READY.canChangeTo(OrderStatus.PAID));
        Assertions.assertFalse(OrderStatus.CANCELED.canChangeTo(OrderStatus.DRAFT));
    }

}