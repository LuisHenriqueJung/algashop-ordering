package com.jung.algashop.ordering.domain.model.exception;

import com.jung.algashop.ordering.domain.model.valueobject.id.ProductId;

public class ProductOutOfStockException extends DomainException {
    public ProductOutOfStockException(ProductId id) {
        super(String.format(ErrorMessages.PRODUCT_OUT_OF_STOCK, id));
    }
}
