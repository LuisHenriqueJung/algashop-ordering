package com.jung.algashop.ordering.domain.exception;

import com.jung.algashop.ordering.domain.valueobject.id.ShoppingCartId;
import com.jung.algashop.ordering.domain.valueobject.id.ShoppingCartItemId;

public class ShoppingCartDoesNotContainItemException extends DomainException {
    public ShoppingCartDoesNotContainItemException(ShoppingCartId cartId, ShoppingCartItemId itemId) {
        super(String.format("Shopping cart %s does not contain item %s", cartId, itemId));
    }
}
