package ca.bgcengineering.promogearreworked.api.officelocations.officelocation.secured.dto;

import ca.bgcengineering.promogearreworked.api.officelocations.officelocation.secured.dto.validation.uniqueofficename.UniqueOfficeName;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
public class SecuredOfficeLocationPartialUpdate {

    @JsonIgnore
    @Setter
    private Long id;

    @Size(min = 1, max = 60)
    @UniqueOfficeName
    private final String name;

    @Size(min = 1, max = 60)
    private final String addressLine1;

    @Size(min = 1, max = 60)
    private final String addressLine2;

    @Size(min = 1, max = 60)
    private final String city;

    @Size(min = 1, max = 60)
    private final String state;

    @Size(min = 1, max = 60)
    private final String country;

    @Size(min = 1, max = 60)
    private final String zipCode;

    @JsonCreator
    public SecuredOfficeLocationPartialUpdate(@JsonProperty("name") String name,
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
