package com.example.bgcpromogearreworked.api.categories.category.dto.secured;

import com.example.bgcpromogearreworked.api.shared.validation.constraints.categoryexists.CategoryExists;
import com.example.bgcpromogearreworked.api.categories.category.dto.secured.validation.nameuniqueforparent.CategoryNameUniqueForParent;
import com.example.bgcpromogearreworked.api.categories.category.dto.secured.validation.parentnotchild.CategoryParentNotChild;
import com.example.bgcpromogearreworked.api.categories.category.dto.secured.validation.parentnotself.CategoryParentNotSelf;
import com.example.bgcpromogearreworked.api.shared.validation.groups.FirstValidationGroup;
import com.example.bgcpromogearreworked.api.shared.validation.groups.SecondValidationGroup;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@GroupSequence({SecuredCategoryUpdate.class, FirstValidationGroup.class, SecondValidationGroup.class})
@CategoryParentNotSelf(groups = SecondValidationGroup.class)
@CategoryParentNotChild(groups = SecondValidationGroup.class)
@CategoryNameUniqueForParent
public class SecuredCategoryUpdate {

    @JsonIgnore
    @Setter
    private Long id;

    @Size(min = 1, max = 30)
    @NotNull
    private final String name;

    @CategoryExists(groups = FirstValidationGroup.class)
    private final Long parentId;

    @JsonCreator
    SecuredCategoryUpdate(@JsonProperty String name, @JsonProperty Long parentId) {
        this.name = name;
        this.parentId = parentId;
    }

}
