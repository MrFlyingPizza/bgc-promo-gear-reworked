package com.example.bgcpromogearreworked.api.products.product.dto.secured;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class SecuredProductResponse {

    @Getter
    @RequiredArgsConstructor
    static class NestedCategory {

        private final Long id;
        private final String name;
        private final Long parentId;
        private final String parentName;
    }

    @Getter
    @RequiredArgsConstructor
    static class NestedOption {
        private final String name;
        private final List<String> values;
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
        private final Instant createdDate;
        private final Instant lastModifiedDate;
        private final NestedUser createdBy;
        private final NestedUser lastModifiedBy;
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
    static class NestedUser {
        private final Long id;
        private final String firstName;
        private final String lastName;
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
    private final Instant createdDate;
    private final Instant lastModifiedDate;
    private final NestedUser createdBy;
    private final NestedUser lastModifiedBy;
}
