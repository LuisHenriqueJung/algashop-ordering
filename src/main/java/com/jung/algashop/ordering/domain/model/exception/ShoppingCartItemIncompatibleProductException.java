package com.jung.algashop.ordering.domain.model.exception;

import com.jung.algashop.ordering.domain.model.valueobject.id.ProductId;

public class ShoppingCartItemIncompatibleProductException extends DomainException {
    public ShoppingCartItemIncompatibleProductException(ProductId expectedProductId, ProductId actualProductId) {
        super(String.format("Shopping cart item incompatible product. Expected %s but got %s", expectedProductId, actualProductId));
    }
}
