package com.jung.algashop.ordering.domain.model.entity;

import com.jung.algashop.ordering.domain.model.exception.ShoppingCartItemIncompatibleProductException;
import com.jung.algashop.ordering.domain.model.valueobject.Money;
import com.jung.algashop.ordering.domain.model.valueobject.Product;
import com.jung.algashop.ordering.domain.model.valueobject.ProductName;
import com.jung.algashop.ordering.domain.model.valueobject.Quantity;
import com.jung.algashop.ordering.domain.model.valueobject.id.ProductId;
import com.jung.algashop.ordering.domain.model.valueobject.id.ShoppingCartId;
import com.jung.algashop.ordering.domain.model.valueobject.id.ShoppingCartItemId;
import lombok.Builder;

import java.util.Objects;

public class ShoppingCartItem {

    private ShoppingCartItemId id;
    private ShoppingCartId shoppingCartId;

    private ProductId productId;
    private ProductName productName;

    private Money price;
    private Boolean inStock;

    private Quantity quantity;

    private Money totalAmount;

    @Builder(builderClassName = "ExistingShoppingCartItemBuilder", builderMethodName = "existing")
    public ShoppingCartItem(ShoppingCartItemId id,
                            ShoppingCartId shoppingCartId,
                            ProductId productId,
                            ProductName productName,
                            Money price,
                            Boolean inStock,
                            Quantity quantity,
                            Money totalAmount) {
        this.setId(id);
        this.setShoppingCartId(shoppingCartId);
        this.setProductId(productId);
        this.setProductName(productName);
        this.setPrice(price);
        this.setInStock(inStock);
        this.setQuantity(quantity);
        this.setTotalAmount(totalAmount);
    }

    @Builder(builderClassName = "BrandNewShoppingCartItemBuilder", builderMethodName = "brandNew")
    private static ShoppingCartItem createBrandNew(ShoppingCartId shoppingCartId, Product product, Quantity quantity) {
        Objects.requireNonNull(shoppingCartId);
        Objects.requireNonNull(product);
        Objects.requireNonNull(quantity);

        ShoppingCartItem item = new ShoppingCartItem(
                new ShoppingCartItemId(),
                shoppingCartId,
                product.id(),
                product.name(),
                product.price(),
                product.inStock(),
                quantity,
                Money.ZERO
        );
        item.recalculateTotals();
        return item;
    }

    public void refresh(Product product) {
        Objects.requireNonNull(product);

        if (!this.productId().equals(product.id())) {
            throw new ShoppingCartItemIncompatibleProductException(this.productId(), product.id());
        }

        this.setProductName(product.name());
        this.setPrice(product.price());
        this.setInStock(product.inStock());
        this.recalculateTotals();
    }

    public void changeQuantity(Quantity quantity) {
        Objects.requireNonNull(quantity);

        if (quantity.value() < 1) {
            throw new IllegalArgumentException("Quantity should be positive");
        }

        this.setQuantity(quantity);
        this.recalculateTotals();
    }

    private void recalculateTotals() {
        this.setTotalAmount(this.price().multiply(this.quantity()));
    }

    private void setId(ShoppingCartItemId id) {
        Objects.requireNonNull(id);
        this.id = id;
    }

    private void setShoppingCartId(ShoppingCartId shoppingCartId) {
        Objects.requireNonNull(shoppingCartId);
        this.shoppingCartId = shoppingCartId;
    }

    private void setProductId(ProductId productId) {
        Objects.requireNonNull(productId);
        this.productId = productId;
    }

    private void setProductName(ProductName productName) {
        Objects.requireNonNull(productName);
        this.productName = productName;
    }

    private void setPrice(Money price) {
        Objects.requireNonNull(price);
        this.price = price;
    }

    private void setInStock(Boolean inStock) {
        Objects.requireNonNull(inStock);
        this.inStock = inStock;
    }

    private void setQuantity(Quantity quantity) {
        Objects.requireNonNull(quantity);
        this.quantity = quantity;
    }

    private void setTotalAmount(Money totalAmount) {
        Objects.requireNonNull(totalAmount);
        this.totalAmount = totalAmount;
    }

    public ShoppingCartItemId id() {
        return id;
    }

    public ShoppingCartId shoppingCartId() {
        return shoppingCartId;
    }

    public ProductId productId() {
        return productId;
    }

    public ProductName productName() {
        return productName;
    }

    public Money price() {
        return price;
    }

    public Boolean inStock() {
        return inStock;
    }

    public Quantity quantity() {
        return quantity;
    }

    public Money totalAmount() {
        return totalAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ShoppingCartItem that)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
