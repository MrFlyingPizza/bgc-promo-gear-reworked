package com.example.bgcpromogearreworked.api.products.product.dto.secured;

import com.example.bgcpromogearreworked.api.shared.validation.category.annotations.CategoryExists;
import com.example.bgcpromogearreworked.api.products.product.dto.secured.validation.ProductPublishable;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@ProductPublishable
public class SecuredProductPartialUpdate {

    @JsonIgnore
    @Setter
    private Long id;

    @Size(min = 1, max = 60)
    private final String name;

    @Size(min = 1, max = 60)
    private final String brand;

    @CategoryExists
    private final Long categoryId;

    private final String description;

    @Digits(integer = 10, fraction = 2)
    @DecimalMin(value = "0.00")
    @DecimalMax(value = "99999999.99")
    private final BigDecimal price;

    private final Boolean isPublished;

    private final Boolean isBigItem;

    private final Boolean isWaitListEnabled;

    @JsonCreator
    private SecuredProductPartialUpdate(@JsonProperty String name,
                                        @JsonProperty String brand,
                                        @JsonProperty Long categoryId,
                                        @JsonProperty String description,
                                        @JsonProperty BigDecimal price,
                                        @JsonProperty Boolean isPublished,
                                        @JsonProperty Boolean isBigItem,
                                        @JsonProperty Boolean isWaitListEnabled) {
        this.name = name;
        this.brand = brand;
        this.categoryId = categoryId;
        this.description = description;
        this.price = price;
        this.isPublished = isPublished;
        this.isBigItem = isBigItem;
        this.isWaitListEnabled = isWaitListEnabled;
    }
}
