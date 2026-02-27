package com.jung.algashop.ordering.domain.exception;

import com.jung.algashop.ordering.domain.valueobject.id.OrderId;

public class OrderInvalidShippingDeliveryDateException extends RuntimeException {
    public OrderInvalidShippingDeliveryDateException(String message) {
        super(message);
    }

    public OrderInvalidShippingDeliveryDateException(OrderId id) {
        this(String.format(ErrorMessages.ORDER_INVALID_SHIPPING_DELIVERY_DATE_CANNOT_BE_IN_PAST, id));
    }
}
