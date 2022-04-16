package ca.bgcengineering.promogearreworked.api.officelocations.officelocation.general;

import ca.bgcengineering.promogearreworked.api.officelocations.officelocation.OfficeLocationService;
import ca.bgcengineering.promogearreworked.persistence.entities.OfficeLocation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneralOfficeLocationHandlerService {

    private final OfficeLocationService service;

    OfficeLocation handleOfficeLocationGet(Long locationId) {
        return service.getOfficeLocation(locationId);
    }

    List<OfficeLocation> handleOfficeLocationBatchGet() {
        return service.getOfficeLocations();
    }

}
