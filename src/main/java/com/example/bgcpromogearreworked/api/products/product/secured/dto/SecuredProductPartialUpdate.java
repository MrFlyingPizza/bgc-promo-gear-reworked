package com.example.bgcpromogearreworked.api.products.product.secured.dto;

import com.example.bgcpromogearreworked.api.shared.validation.constraints.categoryexists.CategoryExists;
import com.example.bgcpromogearreworked.api.shared.validation.constraints.optionexists.OptionExists;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
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

    @UniqueElements
    private final List<@NotNull @OptionExists Long> optionIds;

    @JsonCreator
    private SecuredProductPartialUpdate(@JsonProperty("name") String name,
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
