package ca.bgcengineering.promogearreworked.api.officelocations.officelocation.general.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class GeneralOfficeLocationBatchResponse {

    private final List<GeneralOfficeLocationResponse> officeLocations;

}
