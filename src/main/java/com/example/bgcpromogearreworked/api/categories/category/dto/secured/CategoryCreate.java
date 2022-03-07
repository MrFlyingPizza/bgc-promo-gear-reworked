package com.example.bgcpromogearreworked.api.categories.category.dto.secured;

import com.example.bgcpromogearreworked.api.common.validation.category.annotations.CategoryExists;
import com.example.bgcpromogearreworked.api.common.validation.category.annotations.CategoryNameUniqueForParent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@CategoryNameUniqueForParent
public class CategoryCreate {

    @NotNull
    @Size(min = 1, max = 30)
    private final String name;

    @CategoryExists
    private final Long parentId;

    @JsonCreator
    CategoryCreate(@JsonProperty String name, @JsonProperty Long parentId) {
        this.name = name;
        this.parentId = parentId;
    }
}
