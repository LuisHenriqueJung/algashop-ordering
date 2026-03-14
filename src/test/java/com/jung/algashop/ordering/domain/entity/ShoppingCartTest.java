package com.jung.algashop.ordering.domain.entity;

import com.jung.algashop.ordering.domain.model.entity.ShoppingCart;
import com.jung.algashop.ordering.domain.model.entity.ShoppingCartItem;
import com.jung.algashop.ordering.domain.model.exception.ProductOutOfStockException;
import com.jung.algashop.ordering.domain.model.exception.ShoppingCartDoesNotContainItemException;
import com.jung.algashop.ordering.domain.model.exception.ShoppingCartDoesNotContainProductException;
import com.jung.algashop.ordering.domain.model.valueobject.Money;
import com.jung.algashop.ordering.domain.model.valueobject.Product;
import com.jung.algashop.ordering.domain.model.valueobject.ProductName;
import com.jung.algashop.ordering.domain.model.valueobject.Quantity;
import com.jung.algashop.ordering.domain.model.valueobject.id.CustomerId;
import com.jung.algashop.ordering.domain.model.valueobject.id.ShoppingCartId;
import com.jung.algashop.ordering.domain.model.valueobject.id.ShoppingCartItemId;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ShoppingCartTest {

    @Test
    public void givenNewCart_whenStartShopping_shouldBeEmptyAndZeroed() {
        ShoppingCart cart = ShoppingCart.startShopping(new CustomerId());

        Assertions.assertWith(cart,
                (c) -> Assertions.assertThat(c.id()).isNotNull(),
                (c) -> Assertions.assertThat(c.totalAmount()).isEqualTo(Money.ZERO),
                (c) -> Assertions.assertThat(c.totalItems()).isEqualTo(Quantity.ZERO),
                (c) -> Assertions.assertThat(c.isEmpty()).isTrue()
        );
    }

    @Test
    public void givenOutOfStockProduct_whenAddItem_shouldThrowException() {
        ShoppingCart cart = ShoppingCart.startShopping(new CustomerId());

        Assertions.assertThatExceptionOfType(ProductOutOfStockException.class)
                .isThrownBy(() -> cart.addItem(ProductTestDataBuilder.aProductUnavailable().build(), Quantity.ONE));
    }

    @Test
    public void givenSameProductAddedTwice_whenAddItem_shouldSumQuantitiesAndRecalculateTotals() {
        ShoppingCart cart = ShoppingCart.startShopping(new CustomerId());
        Product product = ProductTestDataBuilder.aProductAltMousePad().build();

        cart.addItem(product, new Quantity(1));
        Product updatedProduct = Product.builder()
                .id(product.id())
                .inStock(product.inStock())
                .price(new Money("150"))
                .name(new ProductName("Mouse Pad v2"))
                .build();
        cart.addItem(updatedProduct, new Quantity(2));

        Assertions.assertThat(cart.items().size()).isEqualTo(1);
        ShoppingCartItem item = cart.items().iterator().next();

        Assertions.assertWith(item,
                (i) -> Assertions.assertThat(i.quantity()).isEqualTo(new Quantity(3)),
                (i) -> Assertions.assertThat(i.price()).isEqualTo(new Money("150")),
                (i) -> Assertions.assertThat(i.productName()).isEqualTo(new ProductName("Mouse Pad v2"))
        );

        Assertions.assertThat(cart.totalItems()).isEqualTo(new Quantity(3));
        Assertions.assertThat(cart.totalAmount()).isEqualTo(new Money("450"));
    }

    @Test
    public void givenDifferentProducts_whenAddItemTwice_shouldCreateTwoItems() {
        ShoppingCart cart = ShoppingCart.startShopping(new CustomerId());

        cart.addItem(ProductTestDataBuilder.aProductAltMousePad().build(), new Quantity(1));
        cart.addItem(ProductTestDataBuilder.aProductAltRamMemory().build(), new Quantity(2));

        Assertions.assertThat(cart.items().size()).isEqualTo(2);
        Assertions.assertThat(cart.totalItems()).isEqualTo(new Quantity(3));
        Assertions.assertThat(cart.totalAmount()).isEqualTo(new Money("500"));
    }

    @Test
    public void givenCart_whenRemoveNonExistingItem_shouldThrowException() {
        ShoppingCart cart = ShoppingCart.startShopping(new CustomerId());

        Assertions.assertThatExceptionOfType(ShoppingCartDoesNotContainItemException.class)
                .isThrownBy(() -> cart.removeItem(new ShoppingCartItemId("missing")));
    }

    @Test
    public void givenCartWithItems_whenEmpty_shouldRemoveAllAndZeroTotals() {
        ShoppingCart cart = ShoppingCart.startShopping(new CustomerId());
        cart.addItem(ProductTestDataBuilder.aProductAltMousePad().build(), new Quantity(1));
        cart.addItem(ProductTestDataBuilder.aProductAltRamMemory().build(), new Quantity(2));

        cart.empty();

        Assertions.assertThat(cart.isEmpty()).isTrue();
        Assertions.assertThat(cart.totalItems()).isEqualTo(Quantity.ZERO);
        Assertions.assertThat(cart.totalAmount()).isEqualTo(Money.ZERO);
    }

    @Test
    public void givenCart_whenRefreshItemWithProductNotInCart_shouldThrowException() {
        ShoppingCart cart = ShoppingCart.startShopping(new CustomerId());
        cart.addItem(ProductTestDataBuilder.aProductAltMousePad().build(), new Quantity(1));

        Assertions.assertThatExceptionOfType(ShoppingCartDoesNotContainProductException.class)
                .isThrownBy(() -> cart.refreshItem(ProductTestDataBuilder.aProductAltRamMemory().build()));
    }

    @Test
    public void givenCartsWithSameId_whenCompare_shouldBeEqual() {
        ShoppingCartId id = new ShoppingCartId("same");

        ShoppingCart cart1 = ShoppingCart.existing()
                .id(id)
                .customerId(new CustomerId())
                .totalAmount(Money.ZERO)
                .totalItems(Quantity.ZERO)
                .startedAt(java.time.OffsetDateTime.now())
                .items(new java.util.HashSet<>())
                .build();

        ShoppingCart cart2 = ShoppingCart.existing()
                .id(id)
                .customerId(new CustomerId())
                .totalAmount(new Money("10"))
                .totalItems(new Quantity(1))
                .startedAt(java.time.OffsetDateTime.now().minusDays(1))
                .items(new java.util.HashSet<>())
                .build();

        Assertions.assertThat(cart1).isEqualTo(cart2);
    }
}
