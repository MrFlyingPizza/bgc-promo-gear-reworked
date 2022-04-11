package com.example.bgcpromogearreworked.api.officelocations.officelocation.secured;

import com.example.bgcpromogearreworked.api.officelocations.officelocation.secured.dto.SecuredOfficeLocationCreate;
import com.example.bgcpromogearreworked.api.officelocations.officelocation.OfficeLocationService;
import com.example.bgcpromogearreworked.api.officelocations.officelocation.secured.dto.SecuredOfficeLocationMapper;
import com.example.bgcpromogearreworked.api.officelocations.officelocation.secured.dto.SecuredOfficeLocationPartialUpdate;
import com.example.bgcpromogearreworked.api.officelocations.officelocation.secured.dto.SecuredOfficeLocationUpdate;
import com.example.bgcpromogearreworked.persistence.entities.OfficeLocation;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
@RequiredArgsConstructor
public class SecuredOfficeLocationHandlerService {

    private final OfficeLocationService service;
    private final SecuredOfficeLocationMapper mapper;

    OfficeLocation handleOfficeLocationGet(Long locationId) {
        return service.getOfficeLocation(locationId);
    }

    Page<OfficeLocation> handleOfficeLocationBatchGet(Predicate predicate, Pageable pageable) {
        return service.getOfficeLocations(predicate, pageable);
    }

    OfficeLocation handleOfficeLocationCreate(@Valid SecuredOfficeLocationCreate officeLocationCreate) {
        return service.createOfficeLocation(officeLocationCreate, mapper::fromCreate);
    }

    OfficeLocation handleOfficeLocationUpdate(@Valid SecuredOfficeLocationUpdate officeLocationUpdate) {
        return service.updateOfficeLocation(officeLocationUpdate.getId(), officeLocationUpdate, mapper::fromUpdate);
    }

    OfficeLocation handleOfficeLocationPartialUpdate(@Valid SecuredOfficeLocationPartialUpdate officeLocationPartialUpdate) {
        return service.updateOfficeLocation(officeLocationPartialUpdate.getId(), officeLocationPartialUpdate, mapper::fromPartialUpdate);
    }

}
