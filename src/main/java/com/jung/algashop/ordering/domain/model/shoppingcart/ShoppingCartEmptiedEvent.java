package com.jung.algashop.ordering.domain.model.shoppingcart;

import java.time.OffsetDateTime;

public record ShoppingCartEmptiedEvent(ShoppingCartId shoppingCartId, OffsetDateTime emptiedAt) {
}
