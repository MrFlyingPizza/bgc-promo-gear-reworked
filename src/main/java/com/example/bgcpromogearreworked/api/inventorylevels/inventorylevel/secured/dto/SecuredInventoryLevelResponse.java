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
    static class NestedVariant {

        @Getter
        @RequiredArgsConstructor
        static class NestedOptionValue {
            private final Long id;
            private final Long optionId;
            private final String name;
            private final String value;
        }

        private final Long id;
        private final Long productId;
        private final String productName;
        private final List<NestedOptionValue> options;
    }

    @Getter
    @RequiredArgsConstructor
    static class NestedUser {
        private final Long id;
        private final String displayName;
    }

    private final NestedVariant variant;
    private final NestedLocation location;
    private final Instant lastManuallyModifiedDate;
    private final NestedUser lastManuallyModifiedBy;

}
