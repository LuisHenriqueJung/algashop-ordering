package com.jung.algashop.ordering.domain.exception;

import com.jung.algashop.ordering.domain.valueobject.id.ProductId;
import com.jung.algashop.ordering.domain.valueobject.id.ShoppingCartId;

public class ShoppingCartDoesNotContainProductException extends DomainException {
    public ShoppingCartDoesNotContainProductException(ShoppingCartId cartId, ProductId productId) {
        super(String.format("Shopping cart %s does not contain product %s", cartId, productId));
    }
}
