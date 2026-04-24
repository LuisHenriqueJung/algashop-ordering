package com.jung.algashop.ordering.domain.model.order;

import com.jung.algashop.ordering.domain.model.Specification;
import com.jung.algashop.ordering.domain.model.customer.Customer;
import lombok.RequiredArgsConstructor;

import java.time.Year;

@RequiredArgsConstructor
public class CustomerHasOrderedEnoughtAtYearSpecification implements Specification<Customer> {

    private final Orders orders;
    private final long expectedAmount;

    @Override
    public boolean isSatisfiedBy(Customer customer) {
        return orders.salesQuantityByCustomerInYear(
                customer.id(),
                Year.now()
        ) >= expectedAmount;
    }
}
