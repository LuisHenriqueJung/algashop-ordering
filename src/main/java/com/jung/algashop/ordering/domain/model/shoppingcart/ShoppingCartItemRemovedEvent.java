package com.jung.algashop.ordering.domain.model.shoppingcart;

import com.jung.algashop.ordering.domain.model.commons.Quantity;
import com.jung.algashop.ordering.domain.model.product.ProductId;

import java.time.OffsetDateTime;

public record ShoppingCartItemRemovedEvent(ShoppingCartId shoppingCartId, ShoppingCartItemId shoppingCartItemId,
                                           ProductId productId, Quantity quantity, OffsetDateTime removedAt) {
}
