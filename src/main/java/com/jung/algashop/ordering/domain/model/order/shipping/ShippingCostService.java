package com.jung.algashop.ordering.domain.model.order.shipping;

import com.jung.algashop.ordering.domain.model.commons.Money;
import com.jung.algashop.ordering.domain.model.commons.ZipCode;
import lombok.Builder;

import java.time.LocalDate;

public interface ShippingCostService {
    CalculationResult calculate(CalculationRequest request);

    @Builder
    record CalculationRequest(ZipCode origin, ZipCode destination) {
    }

    @Builder
    record CalculationResult(Money cost, LocalDate expectedDate) {
    }
}
