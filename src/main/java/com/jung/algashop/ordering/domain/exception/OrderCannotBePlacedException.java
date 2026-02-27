package com.jung.algashop.ordering.domain.exception;

import com.jung.algashop.ordering.domain.valueobject.id.OrderId;

public class OrderCannotBePlacedException extends DomainException {
    private OrderCannotBePlacedException(String message) {
        super(message);
    }

    public static OrderCannotBePlacedException becauseHasNoItems(OrderId id) {
        return new OrderCannotBePlacedException(String.format(ErrorMessages.ORDER_CANNOT_BE_PLACED_BECAUSE_HAS_NO_ITEMS, id));
    }

    public static OrderCannotBePlacedException becauseHasNoShippingInfo(OrderId id) {
        return new OrderCannotBePlacedException(String.format(ErrorMessages.ORDER_CANNOT_BE_PLACED_BECAUSE_HAS_NO_SHIPPING_INFO, id));
    }

    public static OrderCannotBePlacedException becauseHasNoBillingInfo(OrderId id) {
        return new OrderCannotBePlacedException(String.format(ErrorMessages.ORDER_CANNOT_BE_PLACED_BECAUSE_HAS_NO_BILLING_INFO, id));
    }

    public static OrderCannotBePlacedException becauseHasNoPaymentMethod(OrderId id) {
        return new OrderCannotBePlacedException(String.format(ErrorMessages.ORDER_CANNOT_BE_PLACED_BECAUSE_HAS_NO_PAYMENT_METHOD, id));
    }

    public static OrderCannotBePlacedException becauseHasNoCustomer(OrderId id) {
        return new OrderCannotBePlacedException(String.format(ErrorMessages.ORDER_CANNOT_BE_PLACED_BECAUSE_HAS_NO_CUSTOMER, id));
    }

}
