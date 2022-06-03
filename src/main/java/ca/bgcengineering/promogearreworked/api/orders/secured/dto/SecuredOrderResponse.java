package ca.bgcengineering.promogearreworked.api.orders.secured.dto;

import ca.bgcengineering.promogearreworked.persistence.entities.Order;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class SecuredOrderResponse {

    @Getter
    @RequiredArgsConstructor
    public static class NestedUser {
        private final Long id;
        private final String displayName;
        private final BigDecimal credit;
    }

    @Getter
    @RequiredArgsConstructor
    public static class NestedExtraInfo {
        private final String recipientInfo;
        private final Instant requiredDate;
    }

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
            public static class NestedProductImage {
                private final Long id;
                private final String src;
                private final String alt;
                private final Integer position;
            }

            private final Long id;
            private final NestedProductImage image;
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
            private final String brand;
            private final BigDecimal price;
            private final NestedCategory category;
        }

        private final NestedProduct product;
        private final NestedProductVariant variant;
        private final Integer quantity;
    }

    private final Long id;
    private final NestedUser submitter;
    private final NestedUser fulfiller;
    private final NestedUser recipient;
    private final Order.Status status;
    private final Order.Type type;
    private final String submitterComments;
    private final String fulfillerComments;
    private final NestedOfficeLocation location;
    private final NestedExtraInfo extraInfo;
    private final List<NestedOrderItem> items;
    private final Instant createdDate;
    private final NestedUser createdBy;
    private final Instant lastModifiedDate;
    private final NestedUser lastModifiedBy;

}
