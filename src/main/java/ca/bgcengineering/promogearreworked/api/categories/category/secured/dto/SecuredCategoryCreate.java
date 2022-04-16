package ca.bgcengineering.promogearreworked.api.categories.category.secured.dto;

import ca.bgcengineering.promogearreworked.api.categories.category.secured.dto.validation.nameuniqueforparent.CategoryNameUniqueForParent;
import ca.bgcengineering.promogearreworked.api.categories.category.secured.dto.validation.parentnotchild.CategoryParentNotChild;
import ca.bgcengineering.promogearreworked.api.shared.validation.groups.FirstValidationGroup;
import ca.bgcengineering.promogearreworked.api.shared.validation.groups.SecondValidationGroup;
import ca.bgcengineering.promogearreworked.api.shared.validation.constraints.categoryexists.CategoryExists;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@GroupSequence({SecuredCategoryCreate.class, FirstValidationGroup.class, SecondValidationGroup.class})
@CategoryParentNotChild(groups = SecondValidationGroup.class)
@CategoryNameUniqueForParent
public class SecuredCategoryCreate {

    @NotNull
    @Size(min = 1, max = 30)
    private final String name;

    @CategoryExists
    private final Long parentId;

    @JsonCreator
    public SecuredCategoryCreate(@JsonProperty("name") String name, @JsonProperty("parentId") Long parentId) {
        this.name = name;
        this.parentId = parentId;
    }
}
