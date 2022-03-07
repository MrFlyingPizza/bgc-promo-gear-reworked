package com.example.bgcpromogearreworked.api.products.product.dto.secured;

import com.example.bgcpromogearreworked.api.categories.category.persistence.Category;
import com.example.bgcpromogearreworked.api.products.persistence.*;
import com.example.bgcpromogearreworked.api.users.User;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProductResponse {

    @Getter
    private static class NestedCategory {

        private final Long id;
        private final String name;
        private final Long parentId;
        private final String parentName;

        NestedCategory(Category category) {
            this.id = category.getId();
            this.name = category.getName();

            Category parent = category.getParent();
            if (parent != null) {
                this.parentId = parent.getId();
                this.parentName = parent.getName();
            } else {
                this.parentId = null;
                this.parentName = null;
            }
        }
    }

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
    private static class NestedProductVariant {
        private final Long id;
        private final Long imageId;
        private final Integer waitListThreshold;
        private final List<NestedOptionValue> options;
        private final Instant createdDate;
        private final Instant lastModifiedDate;
        private final NestedUser createdBy;
        private final NestedUser lastModifiedBy;

        NestedProductVariant(ProductVariant variant) {
            this.id = variant.getId();
            this.imageId = variant.getImage() != null ? variant.getImage().getId() : null;
            this.waitListThreshold = variant.getWaitListThreshold();
            this.options = variant.getOptionValues().stream().map(NestedOptionValue::new).collect(Collectors.toList());
            this.createdDate = variant.getCreatedDate();
            this.lastModifiedDate = variant.getLastModifiedDate();
            this.createdBy = new NestedUser(variant.getCreatedBy());
            this.lastModifiedBy = new NestedUser(variant.getLastModifiedBy());
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

    @Getter
    private static class NestedOption {
        private final String name;
        private final List<String> values;

        NestedOption(Option option) {
            this.name = option.getName();
            this.values = option.getValues().stream().map(OptionValue::getValue).collect(Collectors.toList());
        }
    }

    private final Long id;
    private final String name;
    private final String brand;
    private final String description;
    private final BigDecimal price;
    private final Boolean isPublished;
    private final Boolean isBigItem;
    private final Boolean isWaitListEnabled;
    private final NestedCategory category;
    private final List<NestedProductVariant> variants;
    private final List<NestedProductImage> images;
    private final List<NestedOption> options;
    private final Instant createdDate;
    private final Instant lastModifiedDate;
    private final NestedUser createdBy;
    private final NestedUser lastModifiedBy;

    ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.brand = product.getBrand();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.isPublished = product.getIsPublished();
        this.isBigItem = product.getIsBigItem();
        this.isWaitListEnabled = product.getIsWaitListEnabled();
        this.category = new NestedCategory(product.getCategory()); // category is guaranteed to be not null

        this.variants = new ArrayList<>();
        if (product.getVariants() != null) {
            variants.addAll(product.getVariants().stream().map(NestedProductVariant::new).collect(Collectors.toList()));
        }

        this.images = new ArrayList<>();
        if (product.getImages() != null) {
            images.addAll(product.getImages().stream().map(NestedProductImage::new).collect(Collectors.toList()));
        }

        this.options = new ArrayList<>();
        if (product.getOptions() != null) {
            options.addAll(product.getOptions().stream().map(NestedOption::new).collect(Collectors.toList()));
        }

        this.createdDate = product.getCreatedDate();
        this.lastModifiedDate = product.getLastModifiedDate();
        this.createdBy = product.getCreatedBy() == null ? null : new NestedUser(product.getCreatedBy());
        this.lastModifiedBy = product.getLastModifiedBy() == null ? null : new NestedUser(product.getLastModifiedBy());
    }

}
