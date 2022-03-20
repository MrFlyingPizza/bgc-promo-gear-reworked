package com.example.bgcpromogearreworked.api.categories.category.secured.dto;

import com.example.bgcpromogearreworked.api.shared.validation.constraints.categoryexists.CategoryExists;
import com.example.bgcpromogearreworked.api.categories.category.secured.dto.validation.nameuniqueforparent.CategoryNameUniqueForParent;
import com.example.bgcpromogearreworked.api.categories.category.secured.dto.validation.parentnotchild.CategoryParentNotChild;
import com.example.bgcpromogearreworked.api.categories.category.secured.dto.validation.parentnotself.CategoryParentNotSelf;
import com.example.bgcpromogearreworked.api.shared.validation.groups.FirstValidationGroup;
import com.example.bgcpromogearreworked.api.shared.validation.groups.SecondValidationGroup;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.GroupSequence;
import javax.validation.constraints.Size;

@Getter
@GroupSequence({SecuredCategoryPartialUpdate.class, FirstValidationGroup.class, SecondValidationGroup.class})
@CategoryParentNotSelf(groups = SecondValidationGroup.class)
@CategoryParentNotChild(groups = SecondValidationGroup.class)
@CategoryNameUniqueForParent
public class SecuredCategoryPartialUpdate {

    @JsonIgnore
    @Setter
    private Long id;

    @Size(min = 1, max = 30)
    private final String name;

    @CategoryExists(groups = FirstValidationGroup.class)
    private final Long parentId;

    @JsonCreator
    SecuredCategoryPartialUpdate(@JsonProperty("name") String name, @JsonProperty("productId") Long parentId) {
        this.name = name;
        this.parentId = parentId;
    }

}
