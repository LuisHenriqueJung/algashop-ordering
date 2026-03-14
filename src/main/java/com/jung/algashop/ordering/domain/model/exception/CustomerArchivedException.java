package com.jung.algashop.ordering.domain.model.exception;

import static com.jung.algashop.ordering.domain.model.exception.ErrorMessages.CUSTOMER_ARCHIVED;

public class CustomerArchivedException extends DomainException {

    public CustomerArchivedException() {
        super(CUSTOMER_ARCHIVED);
    }

    public CustomerArchivedException(Throwable cause) {
        super(CUSTOMER_ARCHIVED, cause);
    }
}
