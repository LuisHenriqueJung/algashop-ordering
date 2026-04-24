package com.jung.algashop.ordering.domain.model.order;

import com.jung.algashop.ordering.domain.model.Specification;
import com.jung.algashop.ordering.domain.model.customer.Customer;
import com.jung.algashop.ordering.domain.model.customer.LoyaltyPoints;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomerHaveFreeShippingSpecification implements Specification<Customer> {

    private final CustomerHasOrderedEnoughtAtYearSpecification hasOrderedEnoughtAtYear;
    private final CustomerHasEnoughtLoyaltyPointsSpecification hasEnoughtBasicLoyaltyPoints;
    private final CustomerHasEnoughtLoyaltyPointsSpecification hasEnoughtPremiunLoyaltyPoints;


    public CustomerHaveFreeShippingSpecification(Orders orders, LoyaltyPoints basicLoyaltyPoints, long salesForFreeShipping, LoyaltyPoints premiumLoyaltyPoints) {
        this.hasOrderedEnoughtAtYear = new CustomerHasOrderedEnoughtAtYearSpecification(orders, salesForFreeShipping);
        this.hasEnoughtBasicLoyaltyPoints = new CustomerHasEnoughtLoyaltyPointsSpecification(basicLoyaltyPoints);
        this.hasEnoughtPremiunLoyaltyPoints = new CustomerHasEnoughtLoyaltyPointsSpecification(premiumLoyaltyPoints);

    }

    @Override
    public boolean isSatisfiedBy(Customer customer) {
        return hasOrderedEnoughtAtYear
                .and(hasEnoughtBasicLoyaltyPoints)
                .or(hasEnoughtPremiunLoyaltyPoints)
                .isSatisfiedBy(customer);
    }
}
