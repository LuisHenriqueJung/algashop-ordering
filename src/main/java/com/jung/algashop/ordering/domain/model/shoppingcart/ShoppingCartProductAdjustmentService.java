package com.jung.algashop.ordering.domain.model.shoppingcart;

import com.jung.algashop.ordering.domain.model.commons.Money;
import com.jung.algashop.ordering.domain.model.product.ProductId;

public interface ShoppingCartProductAdjustmentService {
    void adjustPrice(ProductId productId, Money updatedPrice);

    void changeAvailability(ProductId productId, boolean available);
}
