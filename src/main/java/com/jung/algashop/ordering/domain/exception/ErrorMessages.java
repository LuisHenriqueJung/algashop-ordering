package com.jung.algashop.ordering.domain.exception;

public class ErrorMessages {
    public static final String VALIDATION_ERROR_EMAIL_IS_INVALID = "Email is invalid";
    public static final String VALIDATION_ERROR_BIRTHDATE_MUST_IN_PAST = "BirthDate must be a past date";
    public static final String VALIDATION_ERROR_FULLNAME_IS_NULL = "FullName cannot be null";
    public static final String VALIDATION_ERROR_FULLNAME_IS_BLANK = "FullName cannot be blank";
    public static final String VALIDATION_ERROR_PHONE_IS_INVALID = "Phone is invalid";
    public static final String CUSTOMER_ARCHIVED = "Customer already archived";
    public static final String LOYALTY_POINTS_SHOULD_BE_POSITIVE = "Loyalty points should be positive";
    public static final String DOCUMENT_IS_BLANK = "Document cannot be blank";
    public static final String MONEY_SHOULD_BE_POSITIVE = "Money should be positive";
    public static final String QUANTITY_SHOULD_BE_POSITIVE = "Quantity should be positive";
    public static final String ORDER_STATUS_CANNOT_BE_CHANGED = "Cannot change order %s status from %s to %s";
    public static final String ORDER_INVALID_SHIPPING_DELIVERY_DATE_CANNOT_BE_IN_PAST = "Order %s invalid shipping delivery date cannot be in past";
    public static final String ORDER_CANNOT_BE_PLACED_BECAUSE_HAS_NO_ITEMS = "Order %s cannot be placed, because it has no items";
    public static final String ORDER_CANNOT_BE_PLACED_BECAUSE_HAS_NO_SHIPPING_INFO = "Order %s cannot be placed, because it has no shipping info";
    public static final String ORDER_CANNOT_BE_PLACED_BECAUSE_HAS_NO_BILLING_INFO = "Order %s cannot be placed, because it has no billing info";
    public static final String ORDER_CANNOT_BE_PLACED_BECAUSE_HAS_NO_PAYMENT_METHOD = "Order %s cannot be placed, because it has no payment method";
    public static final String ORDER_CANNOT_BE_PLACED_BECAUSE_HAS_NO_CUSTOMER = "Order %s cannot be placed, because it has no customer";
    public static final String ORDER_DOES_NOT_CONTAIN_ITEM = "Order %s does not contain item %s";
    public static final String PRODUCT_OUT_OF_STOCK = "Product %s is out of stock";
}