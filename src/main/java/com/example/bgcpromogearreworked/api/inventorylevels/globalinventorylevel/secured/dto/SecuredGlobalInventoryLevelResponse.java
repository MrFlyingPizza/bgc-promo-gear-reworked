package com.example.bgcpromogearreworked.api.inventorylevels.globalinventorylevel.secured.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class SecuredGlobalInventoryLevelResponse {

    @Getter
    @RequiredArgsConstructor
    public static class NestedProduct {

        @Getter
        @RequiredArgsConstructor
        public static class NestedCategory {
            private final Long id;
            private final String name;
            private final NestedCategory parent;
        }

        private final Long id;
        private final String name;
        private final String brand;
        private final NestedCategory category;
    }

    @Getter
    @RequiredArgsConstructor
    public static class NestedProductVariant {

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

    private final NestedProduct product;
    private final NestedProductVariant variant;
    private final Integer totalAvailableQuantity;
    private final Integer totalReservedQuantity;
    private final Integer totalNeededQuantity;
    private final Integer apparentQuantity;

}
