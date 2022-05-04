package ca.bgcengineering.promogearreworked.api.users.cartitem.general.dto;

import ca.bgcengineering.promogearreworked.api.products.variant.ProductVariantAvailability;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class GeneralCartItemResponse {
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
    static class NestedProductImage {
        private final Long id;
        private final String alt;
        private final String src;
    }

    @Getter
    @RequiredArgsConstructor
    static class NestedProductVariant {
        private final Long id;
        private final NestedProductImage image;
        private final ProductVariantAvailability availability;
        private final List<NestedOptionValue> options;
    }

    @Getter
    @RequiredArgsConstructor
    static class NestedProduct {
        private final Long id;
        private final String name;
    }

    private final Long userId;
    private final NestedProduct product;
    private final NestedProductVariant variant;
    private final Integer quantity;

}
