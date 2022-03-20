package com.example.bgcpromogearreworked.api.officelocations.officelocation.general.dto;

import com.example.bgcpromogearreworked.persistence.entities.OfficeLocation;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class GeneralOfficeLocationMapper {

    public abstract GeneralOfficeLocationResponse toResponse(OfficeLocation officeLocation);

    public GeneralOfficeLocationBatchResponse toBatchResponse(List<OfficeLocation> officeLocation) {
        return new GeneralOfficeLocationBatchResponse(officeLocation.stream().map(this::toResponse).collect(Collectors.toList()));
    }
}
