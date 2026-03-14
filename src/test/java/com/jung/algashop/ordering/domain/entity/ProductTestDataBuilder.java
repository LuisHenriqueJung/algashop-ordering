package com.jung.algashop.ordering.domain.entity;

import com.jung.algashop.ordering.domain.model.valueobject.Money;
import com.jung.algashop.ordering.domain.model.valueobject.Product;
import com.jung.algashop.ordering.domain.model.valueobject.ProductName;
import com.jung.algashop.ordering.domain.model.valueobject.id.ProductId;

import java.util.UUID;

public class ProductTestDataBuilder {

    private static final ProductId PRODUCT_ID_DEFAULT = new ProductId(UUID.fromString("00000000-0000-0000-0000-000000000001"));
    private static final ProductId PRODUCT_ID_UNAVAILABLE = new ProductId(UUID.fromString("00000000-0000-0000-0000-000000000002"));
    private static final ProductId PRODUCT_ID_ALT_RAM_MEMORY = new ProductId(UUID.fromString("00000000-0000-0000-0000-000000000003"));
    private static final ProductId PRODUCT_ID_ALT_MOUSE_PAD = new ProductId(UUID.fromString("00000000-0000-0000-0000-000000000004"));

    private ProductTestDataBuilder() {
    }

    public static Product.ProductBuilder aProduct() {
        return Product.builder()
                .id(PRODUCT_ID_DEFAULT)
                .inStock(true)
                .name(new ProductName("Notebook X11"))
                .price(new Money("3000"));
    }

    public static Product.ProductBuilder aProductUnavailable() {
        return Product.builder()
                .id(PRODUCT_ID_UNAVAILABLE)
                .name(new ProductName("Desktop FX9000"))
                .price(new Money("5000"))
                .inStock(false);
    }

    public static Product.ProductBuilder aProductAltRamMemory() {
        return Product.builder()
                .id(PRODUCT_ID_ALT_RAM_MEMORY)
                .name(new ProductName("4GB RAM"))
                .price(new Money("200"))
                .inStock(true);
    }

    public static Product.ProductBuilder aProductAltMousePad() {
        return Product.builder()
                .id(PRODUCT_ID_ALT_MOUSE_PAD)
                .name(new ProductName("Mouse Pad"))
                .price(new Money("100"))
                .inStock(true);
    }

}
