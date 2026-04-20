package com.jung.algashop.ordering.domain.model.shoppingcart;

import com.jung.algashop.ordering.domain.model.DomainException;
import com.jung.algashop.ordering.domain.model.ErrorMessages;
import com.jung.algashop.ordering.domain.model.product.ProductId;

public class ShoppingCartItemIncompatibleProductException extends DomainException {
    public ShoppingCartItemIncompatibleProductException(ShoppingCartItemId id, ProductId productId) {
        super(String.format(ErrorMessages.ERROR_SHOPPING_CART_ITEM_INCOMPATIBLE_PRODUCT, id, productId));
    }
}
