package com.jung.algashop.ordering.domain.model.order;

import com.jung.algashop.ordering.domain.model.Specification;
import com.jung.algashop.ordering.domain.model.customer.Customer;
import com.jung.algashop.ordering.domain.model.customer.LoyaltyPoints;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomerHasEnoughtLoyaltyPointsSpecification implements Specification<Customer> {

    private final LoyaltyPoints ExpectedloyaltyPoints;

    @Override
    public boolean isSatisfiedBy(Customer customer) {
        return customer.loyaltyPoints().compareTo(ExpectedloyaltyPoints) >= 0;
    }
}
