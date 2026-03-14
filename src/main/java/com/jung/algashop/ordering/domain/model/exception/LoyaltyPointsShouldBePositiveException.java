package com.jung.algashop.ordering.domain.model.exception;

import static com.jung.algashop.ordering.domain.model.exception.ErrorMessages.LOYALTY_POINTS_SHOULD_BE_POSITIVE;

public class LoyaltyPointsShouldBePositiveException extends DomainException{

    public LoyaltyPointsShouldBePositiveException() {
        super(LOYALTY_POINTS_SHOULD_BE_POSITIVE);
    }

    public LoyaltyPointsShouldBePositiveException(String message, Throwable cause) {
        super(LOYALTY_POINTS_SHOULD_BE_POSITIVE, cause);
    }
}
