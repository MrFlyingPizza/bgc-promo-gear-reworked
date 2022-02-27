package com.example.bgcpromogearreworked.api.product.secured.product.dto;

import com.example.bgcpromogearreworked.api.category.Category;
import com.example.bgcpromogearreworked.api.product.*;
import com.example.bgcpromogearreworked.api.user.User;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProductResponseDto {

    @Getter
    private static class CategoryDto {

        private final Long id;
        private final String name;
        private final Long parentId;
        private final String parentName;

        CategoryDto(Category category) {
            this.id = category.getId();
            this.name = category.getName();

            Category parent = category.getParent();
            this.parentId = parent.getId();
            this.parentName = parent.getName();
        }
    }

    @Getter
    private static class UserDto {
        private final Long id;
        private final String firstName;
        private final String lastName;

        UserDto(User user) {
            this.id = user.getId();
            this.firstName = user.getFirstName();
            this.lastName = user.getLastName();
        }
    }

    @Getter
    private static class OptionValueDto {
        private final String name;
        private final String value;

        OptionValueDto(OptionValue optionValue) {
            this.name = optionValue.getName();
            this.value = optionValue.getValue();
        }
    }

    @Getter
    private static class ProductVariantDto {
        private final Long id;
        private final Long imageId;
        private final Integer waitListThreshold;
        private final List<OptionValueDto> options;
        private final Instant createdDate;
        private final Instant lastModifiedDate;
        private final UserDto createdBy;
        private final UserDto lastModifiedBy;

        ProductVariantDto(ProductVariant variant) {
            this.id = variant.getId();
            this.imageId = variant.getImageId();
            this.waitListThreshold = variant.getWaitListThreshold();
            this.options = variant.getOptionValues().stream().map(OptionValueDto::new).collect(Collectors.toList());
            this.createdDate = variant.getCreatedDate();
            this.lastModifiedDate = variant.getLastModifiedDate();
            this.createdBy = new UserDto(variant.getCreatedBy());
            this.lastModifiedBy = new UserDto(variant.getLastModifiedBy());
        }
    }

    @Getter
    private static class ProductImageDto {
        private final Long id;
        private final String src;
        private final String alt;
        private final Integer position;

        ProductImageDto(ProductImage productImage) {
            this.id = productImage.getId();
            this.src = productImage.getSrc();
            this.alt = productImage.getAlt();
            this.position = productImage.getPosition();
        }
    }

    @Getter
    private static class OptionDto {
        private final String name;
        private final List<String> values;

        OptionDto(Option option) {
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
    private final CategoryDto category;
    private final List<ProductVariantDto> variants;
    private final List<ProductImageDto> images;
    private final List<OptionDto> options;
    private final Instant createdDate;
    private final Instant lastModifiedDate;
    private final UserDto createdBy;
    private final UserDto lastModifiedBy;

    ProductResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.brand = product.getBrand();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.isPublished = product.getIsPublished();
        this.isBigItem = product.getIsBigItem();
        this.isWaitListEnabled = product.getIsWaitListEnabled();
        this.category = new CategoryDto(product.getCategory());
        this.variants = product.getVariants().stream().map(ProductVariantDto::new).collect(Collectors.toList());
        this.images = product.getImages().stream().map(ProductImageDto::new).collect(Collectors.toList());
        this.options = product.getOptions().stream().map(OptionDto::new).collect(Collectors.toList());
        this.createdDate = product.getCreatedDate();
        this.lastModifiedDate = product.getLastModifiedDate();
        this.createdBy = new UserDto(product.getCreatedBy());
        this.lastModifiedBy = new UserDto(product.getLastModifiedBy());
    }

}
