package ca.bgcengineering.promogearreworked.api.categories.category.secured.dto;

import ca.bgcengineering.promogearreworked.api.shared.validation.constraints.categoryexists.CategoryExists;
import ca.bgcengineering.promogearreworked.api.categories.category.secured.dto.validation.nameuniqueforparent.CategoryNameUniqueForParent;
import ca.bgcengineering.promogearreworked.api.categories.category.secured.dto.validation.parentnotchild.CategoryParentNotChild;
import ca.bgcengineering.promogearreworked.api.categories.category.secured.dto.validation.parentnotself.CategoryParentNotSelf;
import ca.bgcengineering.promogearreworked.api.shared.validation.groups.FirstValidationGroup;
import ca.bgcengineering.promogearreworked.api.shared.validation.groups.SecondValidationGroup;
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
    public SecuredCategoryUpdate(@JsonProperty("name") String name, @JsonProperty("parentId") Long parentId) {
        this.name = name;
        this.parentId = parentId;
    }

}
