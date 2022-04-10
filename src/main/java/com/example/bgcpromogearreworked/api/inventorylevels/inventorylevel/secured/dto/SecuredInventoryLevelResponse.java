package com.example.bgcpromogearreworked.api.inventorylevels.inventorylevel.secured.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class SecuredInventoryLevelResponse {

    @Getter
    @RequiredArgsConstructor
    static class NestedLocation {
        private final Long id;
        private final String name;
    }

    @Getter
    @RequiredArgsConstructor
    static class NestedProductVariant {

        @Getter
        @RequiredArgsConstructor
        static class NestedOptionValue {
            private final Long valueId;
            private final Long optionId;
            private final String name;
            private final String value;
        }

        private final Long id;
        private final Integer waitListThreshold;
        private final List<NestedOptionValue> options;
    }

    @Getter
    @RequiredArgsConstructor
    static class NestedProduct {

        @Getter
        @RequiredArgsConstructor
        static class NestedCategory {
            private final Long id;
            private final String name;
            private final NestedCategory parent;
        }

        private final Long id;
        private final String name;
        private final String brand;
        private final NestedCategory category;
        private final Boolean isPublished;
        private final Boolean isWaitListEnabled;
        private final Boolean isBigItem;
    }

    @Getter
    @RequiredArgsConstructor
    static class NestedUser {
        private final Long id;
        private final String displayName;
    }

    private final Integer availableQuantity;
    private final Integer reservedQuantity;
    private final Integer neededQuantity;
    private final Integer notifyThreshold;
    private final NestedProduct product;
    private final NestedProductVariant variant;
    private final NestedLocation location;
    private final Instant lastManuallyModifiedDate;
    private final NestedUser lastManuallyModifiedBy;

}
