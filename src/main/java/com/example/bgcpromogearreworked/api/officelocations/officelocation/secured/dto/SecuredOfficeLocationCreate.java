package com.example.bgcpromogearreworked.api.officelocations.officelocation.secured.dto;

import com.example.bgcpromogearreworked.api.officelocations.officelocation.secured.dto.validation.uniqueofficename.UniqueOfficeName;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class SecuredOfficeLocationCreate {

    @NotNull
    @UniqueOfficeName
    @Size(min = 1, max = 60)
    private final String name;

    @NotNull
    @Size(min = 1, max = 60)
    private final String addressLine1;

    @Size(min = 1, max = 60)
    private final String addressLine2;

    @NotNull
    @Size(min = 1, max = 60)
    private final String city;

    @NotNull
    @Size(min = 1, max = 60)
    private final String state;

    @NotNull
    @Size(min = 1, max = 60)
    private final String country;

    @NotNull
    @Size(min = 1, max = 60)
    private final String zipCode;

    @JsonCreator
    public SecuredOfficeLocationCreate(@JsonProperty("name") String name,
                                       @JsonProperty("addressLine1") String addressLine1,
                                       @JsonProperty("addressLine2") String addressLine2,
                                       @JsonProperty("city") String city,
                                       @JsonProperty("state") String state,
                                       @JsonProperty("country") String country,
                                       @JsonProperty("zipCode") String zipCode) {
        this.name = name;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
    }
}
