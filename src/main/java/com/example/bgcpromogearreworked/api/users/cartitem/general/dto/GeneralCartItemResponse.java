package com.example.bgcpromogearreworked.api.users.cartitem.general.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class GeneralCartItemResponse {
    @Getter
    @RequiredArgsConstructor
    static class NestedOptionValue {
        private final Long id;
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

    private final Long userId;
    private final Long variantId;
    private final Long productId;
    private final String productName;
    private final NestedImage image;
    private final List<NestedOptionValue> options;
    private final Integer quantity;

}
