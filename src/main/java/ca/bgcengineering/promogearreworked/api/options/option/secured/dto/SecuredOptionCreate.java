package ca.bgcengineering.promogearreworked.api.options.option.secured.dto;

import ca.bgcengineering.promogearreworked.api.options.option.secured.dto.validation.uniqueoptionname.UniqueOptionName;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class SecuredOptionCreate {

    @Size(min = 1, max = 20)
    @NotNull
    @UniqueOptionName
    private final String name;

    @JsonCreator
    public SecuredOptionCreate(@JsonProperty("name") String name) {
        this.name = name;
    }

}
