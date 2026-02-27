package com.jung.algashop.ordering.domain.entity;

import java.util.Arrays;
import java.util.List;

public enum OrderStatus {
    DRAFT,
    PLACED(DRAFT),
    PAID(PLACED),
    READY(PAID),
    CANCELED(PLACED, PAID, READY,DRAFT);

    OrderStatus(OrderStatus... previousStatus){
        this.previousStatus = Arrays.asList(previousStatus);
    }

    private final List<OrderStatus> previousStatus;

    public boolean canChangeTo(OrderStatus newStatus){
        return newStatus.previousStatus.contains(this);
    }

    public boolean canNotChangeTo(OrderStatus newStatus){
        return !canChangeTo(newStatus);
    }
}
