package com.jung.algashop.ordering.domain.exception;

import com.jung.algashop.ordering.domain.valueobject.id.ProductId;

public class ProductOutOfStockException extends DomainException {
    public ProductOutOfStockException(ProductId id) {
        super(String.format(ErrorMessages.PRODUCT_OUT_OF_STOCK, id));
    }
}
