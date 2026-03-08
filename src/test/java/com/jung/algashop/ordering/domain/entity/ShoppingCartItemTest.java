package com.jung.algashop.ordering.domain.entity;

import com.jung.algashop.ordering.domain.exception.ShoppingCartItemIncompatibleProductException;
import com.jung.algashop.ordering.domain.valueobject.Money;
import com.jung.algashop.ordering.domain.valueobject.Quantity;
import com.jung.algashop.ordering.domain.valueobject.id.ShoppingCartId;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ShoppingCartItemTest {

    @Test
    public void givenNewItem_whenCreate_shouldCalculateTotalAmount() {
        ShoppingCartItem item = ShoppingCartItem.brandNew()
                .shoppingCartId(new ShoppingCartId())
                .product(ProductTestDataBuilder.aProductAltMousePad().build())
                .quantity(new Quantity(2))
                .build();

        Assertions.assertThat(item.totalAmount()).isEqualTo(new Money("200"));
    }

    @Test
    public void givenItem_whenChangeQuantityToZero_shouldThrowException() {
        ShoppingCartItem item = ShoppingCartItem.brandNew()
                .shoppingCartId(new ShoppingCartId())
                .product(ProductTestDataBuilder.aProductAltMousePad().build())
                .quantity(new Quantity(1))
                .build();

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> item.changeQuantity(Quantity.ZERO));
    }

    @Test
    public void givenItem_whenRefreshWithIncompatibleProduct_shouldThrowException() {
        ShoppingCartItem item = ShoppingCartItem.brandNew()
                .shoppingCartId(new ShoppingCartId())
                .product(ProductTestDataBuilder.aProductAltMousePad().build())
                .quantity(new Quantity(1))
                .build();

        Assertions.assertThatExceptionOfType(ShoppingCartItemIncompatibleProductException.class)
                .isThrownBy(() -> item.refresh(ProductTestDataBuilder.aProductAltRamMemory().build()));
    }

    @Test
    public void givenItemsWithSameId_whenCompare_shouldBeEqual() {
        ShoppingCartItem existing1 = ShoppingCartItem.existing()
                .id(new com.jung.algashop.ordering.domain.valueobject.id.ShoppingCartItemId("same"))
                .shoppingCartId(new ShoppingCartId("cart"))
                .productId(ProductTestDataBuilder.aProduct().build().id())
                .productName(ProductTestDataBuilder.aProduct().build().name())
                .price(new Money("10"))
                .inStock(true)
                .quantity(new Quantity(1))
                .totalAmount(new Money("10"))
                .build();

        ShoppingCartItem existing2 = ShoppingCartItem.existing()
                .id(new com.jung.algashop.ordering.domain.valueobject.id.ShoppingCartItemId("same"))
                .shoppingCartId(new ShoppingCartId("cart2"))
                .productId(ProductTestDataBuilder.aProductAltMousePad().build().id())
                .productName(ProductTestDataBuilder.aProductAltMousePad().build().name())
                .price(new Money("20"))
                .inStock(true)
                .quantity(new Quantity(2))
                .totalAmount(new Money("40"))
                .build();

        Assertions.assertThat(existing1).isEqualTo(existing2);
    }
}
