package com.example.bgcpromogearreworked.api.products.product.dto.secured;

import com.example.bgcpromogearreworked.api.common.validation.category.annotations.CategoryExists;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
public class ProductCreate {

    @NotNull
    @Size(min = 1, max = 60)
    private final String name;

    @Size(min = 1, max = 60)
    private final String brand;

    @NotNull
    @CategoryExists
    private final Long categoryId;

    @NotNull
    private final String description;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @DecimalMin(value = "0.00")
    @DecimalMax(value = "99999999.99")
    private final BigDecimal price;

    @NotNull
    private final Boolean isBigItem;

    @NotNull
    private final Boolean isWaitListEnabled;

    @JsonCreator
    private ProductCreate(@JsonProperty String name,
                          @JsonProperty String brand,
                          @JsonProperty Long categoryId,
                          @JsonProperty String description,
                          @JsonProperty BigDecimal price,
                          @JsonProperty Boolean isBigItem,
                          @JsonProperty Boolean isWaitListEnabled) {
        this.name = name;
        this.brand = brand;
        this.categoryId = categoryId;
        this.description = description;
        this.price = price;
        this.isBigItem = isBigItem;
        this.isWaitListEnabled = isWaitListEnabled;
    }
}
