package ca.bgcengineering.promogearreworked.api.products.variant.secured.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class SecuredProductVariantResponse {

    @Getter
    @RequiredArgsConstructor
    static class NestedUser {
        private final Long id;
        private final String displayName;
    }

    @Getter
    @RequiredArgsConstructor
    static class NestedProduct {
        private final Long id;
        private final String name;
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
    static class NestedOptionValue {
        private final Long valueId;
        private final Long optionId;
        private final String name;
        private final String value;
    }

    private final Long id;
    private final NestedProductImage image;
    private final Integer waitListThreshold;
    private final Boolean isInUse;
    private final List<NestedOptionValue> options;
    private final Instant createdDate;
    private final Instant lastModifiedDate;
    private final NestedUser createdBy;
    private final NestedUser lastModifiedBy;

}
