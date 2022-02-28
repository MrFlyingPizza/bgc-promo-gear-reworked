package com.example.bgcpromogearreworked.api.products.variant.dto.secured;

import com.example.bgcpromogearreworked.api.products.persistence.OptionValue;
import com.example.bgcpromogearreworked.api.products.persistence.ProductImage;
import com.example.bgcpromogearreworked.api.products.persistence.ProductVariant;
import com.example.bgcpromogearreworked.api.users.User;
import lombok.Getter;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProductVariantResponseDto {

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

    private final Long id;
    private final Long productId;
    private final Integer waitListThreshold;
    private final ProductImageDto image;
    private final List<OptionValueDto> options;
    private final Instant createdDate;
    private final Instant lastModifiedDate;
    private final UserDto createdBy;
    private final UserDto lastModifiedBy;

    ProductVariantResponseDto(ProductVariant productVariant) {
        this.id = productVariant.getId();
        this.productId = productVariant.getProduct().getId();
        this.waitListThreshold = productVariant.getWaitListThreshold();
        this.image = new ProductImageDto(productVariant.getImage());
        this.options = productVariant.getOptionValues().stream().map(OptionValueDto::new).collect(Collectors.toList());
        this.createdDate = productVariant.getCreatedDate();
        this.lastModifiedDate = productVariant.getLastModifiedDate();
        this.createdBy = new UserDto(productVariant.getCreatedBy());
        this.lastModifiedBy = new UserDto(productVariant.getLastModifiedBy());
    }

}