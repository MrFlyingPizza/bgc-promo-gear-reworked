package ca.bgcengineering.promogearreworked.api.products.product.general.dto;

import ca.bgcengineering.promogearreworked.api.products.variant.ProductVariantAvailability;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class GeneralProductResponse {

    @Getter
    @RequiredArgsConstructor
    static class NestedCategory {

        private final Long id;
        private final String name;
        private final NestedCategory parent;
    }

    @Getter
    @RequiredArgsConstructor
    static class NestedProductVariant {

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
        private final ProductVariantAvailability availability;
        private final List<NestedOptionValue> options;
    }

    @Getter
    @RequiredArgsConstructor
    static class NestedOption {
        private final Long id;
        private final String name;
    }

    private final Long id;
    private final String name;
    private final String brand;
    private final String description;
    private final NestedCategory category;
    private final List<NestedProductVariant> variants;
    private final List<NestedOption> options;

}
