package com.example.bgcpromogearreworked.api.options.optionvalue.dto.secured;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class SecuredOptionValueCreate {

    @JsonIgnore
    @Setter
    private String id;

    @Size(min = 1, max = 20)
    @NotNull
    private final String value;

    @JsonCreator
    SecuredOptionValueCreate(@JsonProperty String value) {
        this.value = value;
    }
}
