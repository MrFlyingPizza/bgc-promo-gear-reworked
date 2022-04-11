package ca.bgcengineering.promogearreworked.api.officelocations.officelocation.secured.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@Getter
@RequiredArgsConstructor
public class SecuredOfficeLocationResponse {

    @Getter
    @RequiredArgsConstructor
    static class NestedUser {
        private final Long id;
        private final String displayName;
    }

    private final Long id;
    private final String name;
    private final String addressLine1;
    private final String addressLine2;
    private final String city;
    private final String state;
    private final String country;
    private final String zipCode;
    private final Instant createdDate;
    private final Instant lastModifiedDate;
    private final NestedUser createdBy;
    private final NestedUser lastModifiedBy;

}
