package com.example.bgcpromogearreworked.api.products.product.secured.dto;

import com.example.bgcpromogearreworked.api.shared.validation.constraints.categoryexists.CategoryExists;
import com.example.bgcpromogearreworked.api.shared.validation.constraints.optionexists.OptionExists;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
public class SecuredProductCreate {

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
    @Digits(integer = 8, fraction = 2)
    @DecimalMin(value = "0.00")
    @DecimalMax(value = "99999999.99")
    private final BigDecimal price;

    @NotNull
    @AssertFalse
    private final Boolean isPublished;

    @NotNull
    private final Boolean isBigItem;

    @NotNull
    private final Boolean isWaitListEnabled;

    @NotNull
    @UniqueElements
    private final List<@NotNull @OptionExists Long> optionIds;

    @JsonCreator
    private SecuredProductCreate(@JsonProperty("name") String name,
                                 @JsonProperty("brand") String brand,
                                 @JsonProperty("categoryId") Long categoryId,
                                 @JsonProperty("description") String description,
                                 @JsonProperty("price") BigDecimal price,
                                 @JsonProperty("isPublished") Boolean isPublished,
                                 @JsonProperty("isBigItem") Boolean isBigItem,
                                 @JsonProperty("isWaitListEnabled") Boolean isWaitListEnabled,
                                 @JsonProperty("optionIds") List<Long> optionIds) {
        this.name = name;
        this.brand = brand;
        this.categoryId = categoryId;
        this.description = description;
        this.price = price;
        this.isPublished = isPublished;
        this.isBigItem = isBigItem;
        this.isWaitListEnabled = isWaitListEnabled;
        this.optionIds = optionIds;
    }
}
