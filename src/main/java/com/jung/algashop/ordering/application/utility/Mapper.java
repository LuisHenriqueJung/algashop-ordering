package com.jung.algashop.ordering.application.utility;

public interface Mapper {
    <T> T convert(Object object, Class<T> destinationType);
}
