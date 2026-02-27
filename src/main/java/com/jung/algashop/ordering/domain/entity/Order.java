package com.jung.algashop.ordering.domain.entity;

import com.jung.algashop.ordering.domain.exception.*;
import com.jung.algashop.ordering.domain.valueobject.*;
import com.jung.algashop.ordering.domain.valueobject.id.CustomerId;
import com.jung.algashop.ordering.domain.valueobject.id.OrderId;
import com.jung.algashop.ordering.domain.valueobject.id.OrderItemId;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Order {

    private OrderId id;
    private CustomerId customerId;

    private Money totalAmount;
    private Quantity totalItems;

    private OffsetDateTime placedAt;
    private OffsetDateTime paidAt;
    private OffsetDateTime readyAt;
    private OffsetDateTime cancelledAt;

    private Billing billing;
    private Shipping shipping;

    private OrderStatus status;
    private PaymentMethod paymentMethod;

    private Set<OrderItem> items;

    @Builder(builderClassName = "ExistingOrderBuilder", builderMethodName = "existing")
    public Order(OrderId id, CustomerId customerId, Money totalAmount, Quantity totalItems, OffsetDateTime placedAt, OffsetDateTime paidAt, OffsetDateTime readyAt, OffsetDateTime cancelledAt, Billing billing, OrderStatus status, PaymentMethod paymentMethod, Set<OrderItem> items) {
        this.setId(id);
        this.setCustomerId(customerId);
        this.setTotalAmount(totalAmount);
        this.setTotalItems(totalItems);
        this.setPlacedAt(placedAt);
        this.setPaidAt(paidAt);
        this.setReadyAt(readyAt);
        this.setCancelledAt(cancelledAt);
        this.setBilling(billing);
        this.setShipping(shipping);
        this.setStatus(status);
        this.setPaymentMethod(paymentMethod);
        this.setItems(items);
    }

    public static Order draft(CustomerId customerId) {
        return new Order(
                new OrderId(),
                customerId,
                Money.ZERO,
                Quantity.ZERO,
                null, null, null, null,
                null, OrderStatus.DRAFT,
                null,
                new HashSet<>()
        );
    }

    public void addItem(Product product, Quantity quantity) {
        verifyIfChangeable();
        Objects.requireNonNull(product);
        Objects.requireNonNull(quantity);

        product.checkOutOfStock();

        OrderItem item = OrderItem.brandNew()
                .orderId(this.id())
                .product(product)
                .quantity(quantity)
                .build();
        if (this.items == null) {
            this.items = new HashSet<>();
        }
        this.items.add(item);
        this.recalculateTotals();
    }

    public void changePaymentMethod(PaymentMethod paymentMethod) {
        verifyIfChangeable();
        Objects.requireNonNull(paymentMethod);
        this.setPaymentMethod(paymentMethod);
    }

    public void changeBilling(Billing billing) {
        verifyIfChangeable();
        Objects.requireNonNull(billing);
        this.setBilling(billing);
    }

    public void changeShipping(Shipping shipping) {
        verifyIfChangeable();
        Objects.requireNonNull(shipping);

        if (shipping.expectedDate().isBefore(LocalDate.now())) {
            throw new OrderInvalidShippingDeliveryDateException(this.id());
        }
        this.setShipping(shipping);
    }

    public void changeItemQuantity(OrderItemId itemId, Quantity quantity) {
        verifyIfChangeable();
        Objects.requireNonNull(itemId);
        Objects.requireNonNull(quantity);

        OrderItem orderItem = this.findOrderItem(itemId);
        orderItem.changeQuantity(quantity);
        this.recalculateTotals();
    }

    private OrderItem findOrderItem(OrderItemId itemId) {
        Objects.requireNonNull(itemId);
        return this.items.stream()
                .filter(i -> i.id().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new OrderDoesNotContainItemsException(this.id, itemId));
    }


    public void place() {
        verifyIfCanBePlaced();
        this.setPlacedAt(OffsetDateTime.now());
        this.changeStatus(OrderStatus.PLACED);
    }

    private void verifyIfChangeable () {
        if (!this.isDraft()) {
            throw new OrderCannotBeEditedException(this.id(), this.status());
        }
    }


    private void verifyIfCanBePlaced() {
        if (this.items.isEmpty()) {
            throw OrderCannotBePlacedException.becauseHasNoItems(this.id());
        }
        if (this.shipping() == null) {
            throw OrderCannotBePlacedException.becauseHasNoShippingInfo(this.id());
        }
        if (this.paymentMethod == null) {
            throw OrderCannotBePlacedException.becauseHasNoPaymentMethod(this.id());
        }
        if (this.billing == null) {
            throw OrderCannotBePlacedException.becauseHasNoBillingInfo(this.id());
        }
        if (this.customerId == null) {
            throw OrderCannotBePlacedException.becauseHasNoCustomer(this.id());
        }
    }

    public void markAsPaid() {
        this.setPaidAt(OffsetDateTime.now());
        this.changeStatus(OrderStatus.PAID);
    }

    public boolean isDraft() {
        return OrderStatus.DRAFT.equals(this.status);
    }

    public boolean isPlaced() {
        return OrderStatus.PLACED.equals(this.status);
    }

    public boolean isPaid() {
        return OrderStatus.PAID.equals(this.status);
    }

    private void recalculateTotals() {
        BigDecimal totalItemsAmount = this.items.stream()
                .map(i -> i.totalAmount().value())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        Integer totalItems = this.items.stream()
                .map(i -> i.quantity().value())
                .reduce(0, Integer::sum);

        BigDecimal shipingCost;
        if (this.shipping() == null) {
            shipingCost = BigDecimal.ZERO;
        } else {
            shipingCost = this.shipping().cost().value();
        }
        this.setTotalAmount(new Money(totalItemsAmount.add(shipingCost)));
        this.setTotalItems(new Quantity(totalItems));
    }

    private void changeStatus(OrderStatus newStatus) {
        Objects.requireNonNull(newStatus);
        if (this.status.canNotChangeTo(newStatus)) {
            throw new OrderStatusCannotBeChangedException(this.id(), this.status(), newStatus);
        }
        this.status = newStatus;
    }

    private void setId(OrderId id) {
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

    private void setPlacedAt(OffsetDateTime placedAt) {
        this.placedAt = placedAt;
    }

    private void setPaidAt(OffsetDateTime paidAt) {
        this.paidAt = paidAt;
    }

    private void setReadyAt(OffsetDateTime readyAt) {
        this.readyAt = readyAt;
    }

    private void setCancelledAt(OffsetDateTime cancelledAt) {
        this.cancelledAt = cancelledAt;
    }

    private void setBilling(Billing billing) {
        this.billing = billing;
    }

    private void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    private void setStatus(OrderStatus status) {
        Objects.requireNonNull(status);
        this.status = status;
    }

    private void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }


    private void setItems(Set<OrderItem> items) {
        Objects.requireNonNull(items);
        this.items = items;
    }

    public OrderId id() {
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

    public OffsetDateTime placedAt() {
        return placedAt;
    }

    public OffsetDateTime paidAt() {
        return paidAt;
    }

    public OffsetDateTime readyAt() {
        return readyAt;
    }

    public OffsetDateTime cancelledAt() {
        return cancelledAt;
    }

    public Billing billing() {
        return billing;
    }

    public Shipping shipping() {
        return shipping;
    }

    public OrderStatus status() {
        return status;
    }

    public PaymentMethod paymentMethod() {
        return paymentMethod;
    }

    public Set<OrderItem> items() {
        return Collections.unmodifiableSet(this.items);
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Order order)) return false;

        return id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
