package com.jung.algashop.ordering.domain.entity;

import com.jung.algashop.ordering.domain.exception.ShoppingCartDoesNotContainItemException;
import com.jung.algashop.ordering.domain.exception.ShoppingCartDoesNotContainProductException;
import com.jung.algashop.ordering.domain.valueobject.Money;
import com.jung.algashop.ordering.domain.valueobject.Product;
import com.jung.algashop.ordering.domain.valueobject.Quantity;
import com.jung.algashop.ordering.domain.valueobject.id.CustomerId;
import com.jung.algashop.ordering.domain.valueobject.id.ProductId;
import com.jung.algashop.ordering.domain.valueobject.id.ShoppingCartId;
import com.jung.algashop.ordering.domain.valueobject.id.ShoppingCartItemId;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ShoppingCart {

    private ShoppingCartId id;
    private CustomerId customerId;

    private Money totalAmount;
    private Quantity totalItems;

    private OffsetDateTime startedAt;

    private Set<ShoppingCartItem> items;

    @Builder(builderClassName = "ExistingShoppingCartBuilder", builderMethodName = "existing")
    public ShoppingCart(ShoppingCartId id,
                        CustomerId customerId,
                        Money totalAmount,
                        Quantity totalItems,
                        OffsetDateTime startedAt,
                        Set<ShoppingCartItem> items) {
        this.setId(id);
        this.setCustomerId(customerId);
        this.setTotalAmount(totalAmount);
        this.setTotalItems(totalItems);
        this.setStartedAt(startedAt);
        this.setItems(items);
    }

    public static ShoppingCart startShopping(CustomerId customerId) {
        Objects.requireNonNull(customerId);

        return new ShoppingCart(
                new ShoppingCartId(),
                customerId,
                Money.ZERO,
                Quantity.ZERO,
                OffsetDateTime.now(),
                new HashSet<>()
        );
    }

    public void empty() {
        if (this.items == null || this.items.isEmpty()) {
            this.recalculateTotals();
            return;
        }

        Set<ShoppingCartItemId> ids = this.items.stream()
                .map(ShoppingCartItem::id)
                .collect(java.util.stream.Collectors.toSet());

        ids.forEach(this::removeItem);
    }

    public void addItem(Product product, Quantity quantity) {
        Objects.requireNonNull(product);
        Objects.requireNonNull(quantity);

        product.checkOutOfStock();

        ShoppingCartItem item;
        try {
            item = this.findItem(product.id());
            item.changeQuantity(item.quantity().add(quantity));
            item.refresh(product);
        } catch (ShoppingCartDoesNotContainProductException e) {
            item = ShoppingCartItem.brandNew()
                    .shoppingCartId(this.id())
                    .product(product)
                    .quantity(quantity)
                    .build();

            if (this.items == null) {
                this.items = new HashSet<>();
            }

            this.items.add(item);
        }

        this.recalculateTotals();
    }

    public void removeItem(ShoppingCartItemId itemId) {
        Objects.requireNonNull(itemId);

        ShoppingCartItem item = this.findItem(itemId);
        this.items.remove(item);
        this.recalculateTotals();
    }

    public void refreshItem(Product product) {
        Objects.requireNonNull(product);

        ShoppingCartItem item = this.findItem(product.id());
        item.refresh(product);
        this.recalculateTotals();
    }

    public void changeItemQuantity(ShoppingCartItemId itemId, Quantity quantity) {
        Objects.requireNonNull(itemId);
        Objects.requireNonNull(quantity);

        if (quantity.value() < 1) {
            throw new IllegalArgumentException("Quantity should be positive");
        }

        ShoppingCartItem item = this.findItem(itemId);
        item.changeQuantity(quantity);
        this.recalculateTotals();
    }

    public ShoppingCartItem findItem(ShoppingCartItemId itemId) {
        Objects.requireNonNull(itemId);

        return this.items.stream()
                .filter(i -> i.id().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new ShoppingCartDoesNotContainItemException(this.id(), itemId));
    }

    public ShoppingCartItem findItem(ProductId productId) {
        Objects.requireNonNull(productId);

        return this.items.stream()
                .filter(i -> i.productId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new ShoppingCartDoesNotContainProductException(this.id(), productId));
    }

    private void recalculateTotals() {
        if (this.items == null) {
            this.items = new HashSet<>();
        }

        BigDecimal totalItemsAmount = this.items.stream()
                .map(i -> i.totalAmount().value())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Integer totalItems = this.items.stream()
                .map(i -> i.quantity().value())
                .reduce(0, Integer::sum);

        this.setTotalAmount(new Money(totalItemsAmount));
        this.setTotalItems(new Quantity(totalItems));
    }

    public boolean containsUnavailableItems() {
        if (this.items == null || this.items.isEmpty()) {
            return false;
        }

        return this.items.stream().anyMatch(i -> !i.inStock());
    }

    public boolean isEmpty() {
        return this.items == null || this.items.isEmpty();
    }

    private void setId(ShoppingCartId id) {
        Objects.requireNonNull(id);
        this.id = id;
    }

    private void setCustomerId(CustomerId customerId) {
        Objects.requireNonNull(customerId);
        this.customerId = customerId;
    }

    private void setTotalAmount(Money totalAmount) {
        Objects.requireNonNull(totalAmount);
        this.totalAmount = totalAmount;
    }

    private void setTotalItems(Quantity totalItems) {
        Objects.requireNonNull(totalItems);
        this.totalItems = totalItems;
    }

    private void setStartedAt(OffsetDateTime startedAt) {
        Objects.requireNonNull(startedAt);
        this.startedAt = startedAt;
    }

    private void setItems(Set<ShoppingCartItem> items) {
        Objects.requireNonNull(items);
        this.items = items;
    }

    public ShoppingCartId id() {
        return id;
    }

    public CustomerId customerId() {
        return customerId;
    }

    public Money totalAmount() {
        return totalAmount;
    }

    public Quantity totalItems() {
        return totalItems;
    }

    public OffsetDateTime startedAt() {
        return startedAt;
    }

    public Set<ShoppingCartItem> items() {
        return Collections.unmodifiableSet(this.items);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ShoppingCart that)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
