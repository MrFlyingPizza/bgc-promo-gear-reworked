package com.example.bgcpromogearreworked.api.options.optionvalue.secured.dto;

import com.example.bgcpromogearreworked.api.options.optionvalue.secured.dto.validation.uniquenamevalue.UniqueNameValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@UniqueNameValue
public class SecuredOptionValuePartialUpdate {

    @JsonIgnore
    @Setter
    private Long id;

    @JsonIgnore
    @Setter
    private Long optionId;


    @Size(min = 1, max = 20)
    private final String value;

    @JsonCreator
    public SecuredOptionValuePartialUpdate(@JsonProperty("value") String value) {
        this.value = value;
    }
}
