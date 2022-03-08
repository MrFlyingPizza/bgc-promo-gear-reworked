package com.example.bgcpromogearreworked.api.products.variant.dto.secured;

import com.example.bgcpromogearreworked.api.options.persistence.OptionValue;
import com.example.bgcpromogearreworked.api.products.persistence.ProductImage;
import com.example.bgcpromogearreworked.api.products.persistence.ProductVariant;
import com.example.bgcpromogearreworked.api.users.User;
import lombok.Getter;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProductVariantResponse {

    @Getter
    private static class NestedUser {
        private final Long id;
        private final String firstName;
        private final String lastName;

        NestedUser(User user) {
            this.id = user.getId();
            this.firstName = user.getFirstName();
            this.lastName = user.getLastName();
        }
    }

    @Getter
    private static class NestedOptionValue {
        private final String name;
        private final String value;

        NestedOptionValue(OptionValue optionValue) {
            this.name = optionValue.getName();
            this.value = optionValue.getValue();
        }
    }

    @Getter
    private static class NestedProductImage {
        private final Long id;
        private final String src;
        private final String alt;
        private final Integer position;

        NestedProductImage(ProductImage productImage) {
            this.id = productImage.getId();
            this.src = productImage.getSrc();
            this.alt = productImage.getAlt();
            this.position = productImage.getPosition();
        }
    }

    private final Long id;
    private final Long productId;
    private final Integer waitListThreshold;
    private final NestedProductImage image;
    private final List<NestedOptionValue> options;
    private final Instant createdDate;
    private final Instant lastModifiedDate;
    private final NestedUser createdBy;
    private final NestedUser lastModifiedBy;

    ProductVariantResponse(ProductVariant productVariant) {
        this.id = productVariant.getId();
        this.productId = productVariant.getProduct().getId();
        this.waitListThreshold = productVariant.getWaitListThreshold();
        this.image = new NestedProductImage(productVariant.getImage());
        this.options = productVariant.getOptionValues().stream().map(NestedOptionValue::new).collect(Collectors.toList());
        this.createdDate = productVariant.getCreatedDate();
        this.lastModifiedDate = productVariant.getLastModifiedDate();
        this.createdBy = new NestedUser(productVariant.getCreatedBy());
        this.lastModifiedBy = new NestedUser(productVariant.getLastModifiedBy());
    }

}