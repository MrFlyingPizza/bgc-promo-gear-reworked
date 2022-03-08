package com.example.bgcpromogearreworked.api.categories.category.dto.secured;

import com.example.bgcpromogearreworked.api.shared.validation.category.annotations.CategoryExists;
import com.example.bgcpromogearreworked.api.shared.validation.category.annotations.CategoryNameUniqueForParent;
import com.example.bgcpromogearreworked.api.shared.validation.category.annotations.CategoryParentNotChild;
import com.example.bgcpromogearreworked.api.shared.validation.category.annotations.CategoryParentNotSelf;
import com.example.bgcpromogearreworked.api.shared.validation.category.groups.CategoryParentFirstCheck;
import com.example.bgcpromogearreworked.api.shared.validation.category.groups.CategoryParentSecondCheck;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@GroupSequence({SecuredCategoryUpdate.class, CategoryParentFirstCheck.class, CategoryParentSecondCheck.class})
@CategoryParentNotSelf(groups = CategoryParentSecondCheck.class)
@CategoryParentNotChild(groups = CategoryParentSecondCheck.class)
@CategoryNameUniqueForParent
public class SecuredCategoryUpdate {

    @JsonIgnore
    @Setter
    private Long id;

    @Size(min = 1, max = 30)
    @NotNull
    private final String name;

    @CategoryExists(groups = CategoryParentFirstCheck.class)
    private final Long parentId;

    @JsonCreator
    SecuredCategoryUpdate(@JsonProperty String name, @JsonProperty Long parentId) {
        this.name = name;
        this.parentId = parentId;
    }

}
