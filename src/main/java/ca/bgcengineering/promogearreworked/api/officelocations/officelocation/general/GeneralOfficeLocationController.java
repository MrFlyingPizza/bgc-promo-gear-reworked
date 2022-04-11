package ca.bgcengineering.promogearreworked.api.officelocations.officelocation.general;

import ca.bgcengineering.promogearreworked.api.officelocations.officelocation.OfficeLocationService;
import ca.bgcengineering.promogearreworked.api.officelocations.exceptions.OfficeLocationNotFoundException;
import ca.bgcengineering.promogearreworked.api.officelocations.officelocation.general.dto.GeneralOfficeLocationBatchResponse;
import ca.bgcengineering.promogearreworked.api.officelocations.officelocation.general.dto.GeneralOfficeLocationMapper;
import ca.bgcengineering.promogearreworked.api.officelocations.officelocation.general.dto.GeneralOfficeLocationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/office_locations", produces = MediaType.APPLICATION_JSON_VALUE)
public class GeneralOfficeLocationController {

    private final OfficeLocationService service;
    private final GeneralOfficeLocationHandlerService handlerService;
    private final GeneralOfficeLocationMapper mapper;

    @GetMapping("/{locationId}")
    private GeneralOfficeLocationResponse getOfficeLocation(@PathVariable Long locationId) {
        if (!service.checkOfficeLocationExists(locationId)) {
            throw new OfficeLocationNotFoundException();
        }
        return mapper.toResponse(handlerService.handleOfficeLocationGet(locationId));
    }

    @GetMapping
    private GeneralOfficeLocationBatchResponse getOfficeLocationBatch() {
        return mapper.toBatchResponse(handlerService.handleOfficeLocationBatchGet());
    }

}
