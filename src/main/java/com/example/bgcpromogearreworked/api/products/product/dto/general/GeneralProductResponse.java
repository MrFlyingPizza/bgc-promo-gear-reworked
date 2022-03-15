package com.example.bgcpromogearreworked.api.products.product.dto.general;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class GeneralProductResponse {

    @Getter
    @RequiredArgsConstructor
    static class NestedCategory {

        private final Long id;
        private final String name;
        private final NestedCategory parent;
    }

    @Getter
    @RequiredArgsConstructor
    static class NestedOptionValue {
        private final String name;
        private final String value;
    }

    @Getter
    @RequiredArgsConstructor
    static class NestedProductVariant {
        private final Long id;
        private final Long imageId;
        private final Integer waitListThreshold;
        private final List<NestedOptionValue> options;
    }

    @Getter
    @RequiredArgsConstructor
    static class NestedProductImage {
        private final Long id;
        private final String src;
        private final String alt;
        private final Integer position;
    }

    @Getter
    @RequiredArgsConstructor
    static class NestedOption {
        private final String name;
        private final List<String> values;
    }

    private final Long id;
    private final String name;
    private final String brand;
    private final String description;
    private final BigDecimal price;
    private final Boolean isPublished;
    private final Boolean isBigItem;
    private final Boolean isWaitListEnabled;
    private final NestedCategory category;
    private final List<NestedProductVariant> variants;
    private final List<NestedProductImage> images;
    private final List<NestedOption> options;

}
