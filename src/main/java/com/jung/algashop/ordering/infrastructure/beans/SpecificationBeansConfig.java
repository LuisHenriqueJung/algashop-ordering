package com.jung.algashop.ordering.infrastructure.beans;

import com.jung.algashop.ordering.domain.model.customer.LoyaltyPoints;
import com.jung.algashop.ordering.domain.model.order.CustomerHaveFreeShippingSpecification;
import com.jung.algashop.ordering.domain.model.order.Orders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpecificationBeansConfig {

    @Bean
    public CustomerHaveFreeShippingSpecification customerHaveFreeShippingSpecification(Orders orders) {
        return new CustomerHaveFreeShippingSpecification(orders, new LoyaltyPoints(100), 2, new LoyaltyPoints(2000));
    }
}
