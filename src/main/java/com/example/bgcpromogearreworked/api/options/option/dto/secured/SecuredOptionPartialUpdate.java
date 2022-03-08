package com.example.bgcpromogearreworked.api.options.option.dto.secured;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
public class SecuredOptionPartialUpdate {

    @JsonIgnore
    @Setter
    private String id;

    @Size(min = 1, max = 20)
    private final String name;

    @JsonCreator
    SecuredOptionPartialUpdate(@JsonProperty String name) {
        this.name = name;
    }
}
