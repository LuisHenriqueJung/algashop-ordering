package com.jung.algashop.ordering.domain.model.shoppingcart;

import com.jung.algashop.ordering.domain.model.commons.Quantity;
import com.jung.algashop.ordering.domain.model.product.ProductId;

import java.time.OffsetDateTime;

public record ShoppingCartItemAddedEvent(ShoppingCartId shoppingCartId, ProductId productId,
                                         Quantity quantity, OffsetDateTime addedAt) {
}
