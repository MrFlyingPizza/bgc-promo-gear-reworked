package ca.bgcengineering.promogearreworked.api.orders.general.dto;

import ca.bgcengineering.promogearreworked.persistence.entities.Order;
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
        public static class NestedProductVariant {

            @Getter
            @RequiredArgsConstructor
            public static class NestedOptionValue {
                private final Long valueId;
                private final Long optionId;
                private final String name;
                private final String value;
            }

            @Getter
            @RequiredArgsConstructor
            public static class NestedImage {
                private final Long id;
                private final String alt;
                private final String src;
            }

            private final Long id;
            private final NestedImage image;
            private final List<NestedOptionValue> options;
        }

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
            private final NestedCategory category;
        }

        private final NestedProduct product;
        private final NestedProductVariant variant;
        private final Integer quantity;
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
    private final Instant createdDate;
    private final Instant completedDate;
    private final List<NestedOrderItem> items;
}
