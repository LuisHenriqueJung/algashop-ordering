package com.jung.algashop.ordering.domain.exception;

import com.jung.algashop.ordering.domain.valueobject.id.OrderId;
import com.jung.algashop.ordering.domain.valueobject.id.OrderItemId;

public class OrderDoesNotContainOrderItemException extends DomainException{
    public OrderDoesNotContainOrderItemException(OrderId id, OrderItemId itemId) {
        super(String.format(ErrorMessages.ORDER_DOES_NOT_CONTAIN_ITEM,  id, itemId));
    }
}
