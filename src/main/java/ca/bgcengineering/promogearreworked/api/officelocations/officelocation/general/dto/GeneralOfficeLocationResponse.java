package ca.bgcengineering.promogearreworked.api.officelocations.officelocation.general.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GeneralOfficeLocationResponse {

    private final Long id;
    private final String name;
    private final String addressLine1;
    private final String addressLine2;
    private final String city;
    private final String state;
    private final String country;
    private final String zipCode;

}
