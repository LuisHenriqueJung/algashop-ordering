package com.algaworks.algashop.ordering.domain.entity;

import com.algaworks.algashop.ordering.domain.exception.OrderCannotBePlacedException;
import com.algaworks.algashop.ordering.domain.exception.OrderInvalidShippingDeliveryDateException;
import com.algaworks.algashop.ordering.domain.exception.OrderStatusCannotBeChangedException;
import com.algaworks.algashop.ordering.domain.value_object.*;
import com.algaworks.algashop.ordering.domain.value_object.id.CustomerId;
import com.algaworks.algashop.ordering.domain.value_object.id.OrderId;
import com.algaworks.algashop.ordering.domain.value_object.id.ProductId;
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

    private BillingInfo billing;
    private ShippingInfo shipping;

    private OrderStatus status;
    private PaymentMethod paymentMethod;

    private Money shippingCost;
    private LocalDate expectedDeliveryDate;

    private Set<OrderItem> items;

    @Builder(builderClassName = "ExistingOrderBuilder", builderMethodName = "existing")
    public Order(OrderId id, CustomerId customerId, Money totalAmount, Quantity totalItems, OffsetDateTime placedAt, OffsetDateTime paidAt, OffsetDateTime readyAt, OffsetDateTime cancelledAt, BillingInfo billing, ShippingInfo shipping, OrderStatus status, PaymentMethod paymentMethod, Money shippingCost, LocalDate expectedDeliveryDate, Set<OrderItem> items) {
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
        this.setShippingCost(shippingCost);
        this.setExpectedDeliveryDate(expectedDeliveryDate);
        this.setItems(items);
    }

    public static Order draft(CustomerId customerId) {
        return new Order(
                new OrderId(),
                customerId,
                Money.ZERO,
                Quantity.ZERO,
                null, null, null, null,
                null, null, OrderStatus.DRAFT,
                null,
                null,
                null,
                new HashSet<>()

        );
    }

    public void addItem(ProductId productId, ProductName productName, Money price, Quantity quantity) {
        OrderItem item = OrderItem.brandNew()
                .orderId(this.id())
                .productId(productId)
                .productName(productName)
                .price(price)
                .quantity(quantity)
                .build();
        this.items.add(item);
        this.recalculateTotals();
    }

    public void changePaymentMethod(PaymentMethod paymentMethod) {
        Objects.requireNonNull(paymentMethod);
        this.setPaymentMethod(paymentMethod);
    }

    public void changeBilling(BillingInfo billingInfo) {
        Objects.requireNonNull(billingInfo);
        this.setBilling(billingInfo);
    }

    public void changeShipping(ShippingInfo shippingInfo, Money shippingCost, LocalDate expectedDeliveryDate) {
        Objects.requireNonNull(shippingInfo);
        Objects.requireNonNull(shippingCost);
        Objects.requireNonNull(expectedDeliveryDate);

        if (expectedDeliveryDate.isBefore(LocalDate.now())) {
            throw new OrderInvalidShippingDeliveryDateException(this.id());
        }
        this.setShipping(shippingInfo);
        this.setShippingCost(shippingCost);
        this.setExpectedDeliveryDate(expectedDeliveryDate);
    }


    public void place() {
        Objects.requireNonNull(this.billing);
        Objects.requireNonNull(this.shipping);
        Objects.requireNonNull(this.paymentMethod);
        Objects.requireNonNull(this.shippingCost);
        Objects.requireNonNull(this.expectedDeliveryDate);
        Objects.requireNonNull(this.items);
        if(this.items.isEmpty()){
            throw new OrderCannotBePlacedException(this.id());
        }
        this.setPlacedAt(OffsetDateTime.now());
        this.changeStatus(OrderStatus.PLACED);
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
        if (this.shippingCost() == null) {
            shipingCost = BigDecimal.ZERO;
        } else {
            shipingCost = this.shippingCost().value();
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

    private void setBilling(BillingInfo billing) {
        this.billing = billing;
    }

    private void setShipping(ShippingInfo shipping) {
        this.shipping = shipping;
    }

    private void setStatus(OrderStatus status) {
        Objects.requireNonNull(status);
        this.status = status;
    }

    private void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    private void setShippingCost(Money shippingCost) {
        this.shippingCost = shippingCost;
    }

    private void setExpectedDeliveryDate(LocalDate expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
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

    public BillingInfo billing() {
        return billing;
    }

    public ShippingInfo shipping() {
        return shipping;
    }

    public OrderStatus status() {
        return status;
    }

    public PaymentMethod paymentMethod() {
        return paymentMethod;
    }

    public Money shippingCost() {
        return shippingCost;
    }

    public LocalDate expectedDeliveryDate() {
        return expectedDeliveryDate;
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
