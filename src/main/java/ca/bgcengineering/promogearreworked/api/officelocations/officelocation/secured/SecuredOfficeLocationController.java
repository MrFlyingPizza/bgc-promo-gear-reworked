package ca.bgcengineering.promogearreworked.api.officelocations.officelocation.secured;

import ca.bgcengineering.promogearreworked.api.officelocations.exceptions.OfficeLocationNotFoundException;
import ca.bgcengineering.promogearreworked.api.officelocations.officelocation.OfficeLocationService;
import ca.bgcengineering.promogearreworked.api.officelocations.officelocation.secured.dto.*;
import ca.bgcengineering.promogearreworked.persistence.entities.OfficeLocation;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/secured/office_locations", produces = MediaType.APPLICATION_JSON_VALUE)
public class SecuredOfficeLocationController {

    private final OfficeLocationService service;
    private final SecuredOfficeLocationHandlerService handlerService;
    private final SecuredOfficeLocationMapper mapper;

    @GetMapping
    private SecuredOfficeLocationBatchResponse getOfficeLocationBatch(@QuerydslPredicate(root = OfficeLocation.class) Predicate predicate,
                                                                      Pageable pageable) {
        return mapper.toBatchResponse(handlerService.handleOfficeLocationBatchGet(predicate, pageable));
    }

    @GetMapping("/{locationId}")
    private SecuredOfficeLocationResponse getOfficeLocation(@PathVariable Long locationId) {
        if (!service.checkOfficeLocationExists(locationId)) {
            throw new OfficeLocationNotFoundException();
        }
        return mapper.toResponse(handlerService.handleOfficeLocationGet(locationId));
    }

    @PostMapping
    private SecuredOfficeLocationResponse createOfficeLocation(@RequestBody SecuredOfficeLocationCreate officeLocationCreate) {
        return mapper.toResponse(handlerService.handleOfficeLocationCreate(officeLocationCreate));
    }

    @PutMapping("/{locationId}")
    private SecuredOfficeLocationResponse updateOfficeLocation(@PathVariable Long locationId,
                                                               @RequestBody SecuredOfficeLocationUpdate officeLocationUpdate) {
        if (!service.checkOfficeLocationExists(locationId)) {
            throw new OfficeLocationNotFoundException();
        }
        officeLocationUpdate.setId(locationId);
        return mapper.toResponse(handlerService.handleOfficeLocationUpdate(officeLocationUpdate));
    }

    @PatchMapping("/{locationId}")
    private SecuredOfficeLocationResponse updateOfficeLocationPartial(@PathVariable Long locationId,
                                                                      @RequestBody SecuredOfficeLocationPartialUpdate officeLocationPartialUpdate) {
        if (!service.checkOfficeLocationExists(locationId)) {
            throw new OfficeLocationNotFoundException();
        }
        officeLocationPartialUpdate.setId(locationId);
        return mapper.toResponse(handlerService.handleOfficeLocationPartialUpdate(officeLocationPartialUpdate));
    }

    @DeleteMapping("/{locationId}")
    private void deleteOfficeLocation(@PathVariable Long locationId) {
        if (!service.checkOfficeLocationExists(locationId)) {
            throw new OfficeLocationNotFoundException();
        }
        service.deleteOfficeLocation(locationId);
    }
}
