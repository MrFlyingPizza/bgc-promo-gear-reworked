package com.example.bgcpromogearreworked.api.options.option.secured.dto;

import com.example.bgcpromogearreworked.api.options.option.secured.dto.validation.uniqueoptionname.UniqueOptionName;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class SecuredOptionUpdate {

    @JsonIgnore
    @Setter
    private Long id;

    @Size(min = 1, max = 20)
    @NotNull
    @UniqueOptionName
    private final String name;

    @JsonCreator
    SecuredOptionUpdate(@JsonProperty String name) {
        this.name = name;
    }
}
