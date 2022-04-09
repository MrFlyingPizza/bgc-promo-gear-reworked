package com.example.bgcpromogearreworked.api.orders.general.dto;

import com.example.bgcpromogearreworked.persistence.entities.Order;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class GeneralOrderResponse {

    @Getter
    @RequiredArgsConstructor
    public static class NestedOfficeLocation {
        private final Long id;
        private final String name;
    }

    @Getter
    @RequiredArgsConstructor
    public static class NestedOrderItem {

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
        static class NestedImage {
            private final Long id;
            private final String alt;
            private final String src;
        }

        private final Long variantId;
        private final Long productId;
        private final Long productName;
        private final Integer quantity;
        private final NestedImage image;
        private final List<NestedOptionValue> options;
    }

    private final Long id;
    private final String submitter;
    private final String fulfiller;
    private final String recipient;
    private final Order.Status status;
    private final Order.Type type;
    private final String submitterComments;
    private final String fulfillerComments;
    private final NestedOfficeLocation location;
    private final Instant completedDate;
    private final List<NestedOrderItem> items;
// TODO: 2022-03-27 finish implement
}
