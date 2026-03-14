package com.jung.algashop.ordering.domain.model.exception;

import com.jung.algashop.ordering.domain.model.entity.OrderStatus;
import com.jung.algashop.ordering.domain.model.valueobject.id.OrderId;

public class OrderStatusCannotBeChangedException extends DomainException {
    public OrderStatusCannotBeChangedException(OrderId orderId, OrderStatus currentStatus, OrderStatus newStatus) {
        super(String.format(ErrorMessages.ORDER_STATUS_CANNOT_BE_CHANGED, orderId, currentStatus, newStatus));
    }
}
