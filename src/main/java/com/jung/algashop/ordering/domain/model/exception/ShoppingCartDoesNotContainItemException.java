package com.jung.algashop.ordering.domain.model.exception;

import com.jung.algashop.ordering.domain.model.valueobject.id.ShoppingCartId;
import com.jung.algashop.ordering.domain.model.valueobject.id.ShoppingCartItemId;

public class ShoppingCartDoesNotContainItemException extends DomainException {
    public ShoppingCartDoesNotContainItemException(ShoppingCartId cartId, ShoppingCartItemId itemId) {
        super(String.format("Shopping cart %s does not contain item %s", cartId, itemId));
    }
}
