package com.example.bgcpromogearreworked.api.categories.category.dto.secured;

import com.example.bgcpromogearreworked.api.common.validation.CategoryExists;
import com.example.bgcpromogearreworked.api.common.validation.ValidParentCategory;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.Size;

@Getter
public class CategoryUpdateDto {

    @Size(min = 1, max = 30)
    private final String name;

    @CategoryExists
    @ValidParentCategory
    private final Long parentId;

    @JsonCreator
    private CategoryUpdateDto(@JsonProperty String name, @JsonProperty Long parentId) {
        this.name = name;
        this.parentId = parentId;
    }

}
