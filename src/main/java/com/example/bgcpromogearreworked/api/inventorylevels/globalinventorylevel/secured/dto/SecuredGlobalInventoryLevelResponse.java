package com.example.bgcpromogearreworked.api.inventorylevels.globalinventorylevel.secured.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class SecuredGlobalInventoryLevelResponse {

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

        @Getter
        @RequiredArgsConstructor
        static class NestedCategory {
            private final Long id;
            private final String name;
            private final NestedCategory parent;
        }

        private final Long id;
        private final Long productId;
        private final String productName;
        private final NestedCategory category;
        private final List<NestedOptionValue> options;
    }

    private final NestedProductVariant variant;
    private final Integer totalAvailableQuantity;
    private final Integer totalNeededQuantity;
    private final Integer apparentQuantity;

}
