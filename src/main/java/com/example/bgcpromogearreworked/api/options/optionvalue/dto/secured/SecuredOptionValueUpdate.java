package com.example.bgcpromogearreworked.api.options.optionvalue.dto.secured;

import com.example.bgcpromogearreworked.api.options.persistence.OptionValueId;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class SecuredOptionValueUpdate {

    @JsonIgnore
    @Setter
    private OptionValueId id;

    @Size(min = 1, max = 20)
    @NotNull
    private final String value;

    @JsonCreator
    SecuredOptionValueUpdate(@JsonProperty String value) {
        this.value = value;
    }
}
