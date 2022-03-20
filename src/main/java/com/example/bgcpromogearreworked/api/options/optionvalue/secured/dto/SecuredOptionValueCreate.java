package com.example.bgcpromogearreworked.api.options.optionvalue.secured.dto;

import com.example.bgcpromogearreworked.api.options.optionvalue.secured.dto.validation.uniquenamevalue.UniqueNameValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@UniqueNameValue
public class SecuredOptionValueCreate {

    @JsonIgnore
    @Setter
    private Long optionId;

    @Size(min = 1, max = 20)
    @NotNull
    private final String value;

    @JsonCreator
    SecuredOptionValueCreate(@JsonProperty("value") String value) {
        this.value = value;
    }
}
