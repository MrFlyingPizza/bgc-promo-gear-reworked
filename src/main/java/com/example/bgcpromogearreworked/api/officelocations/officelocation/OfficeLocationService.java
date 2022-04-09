package com.example.bgcpromogearreworked.api.officelocations.officelocation;

import com.example.bgcpromogearreworked.api.officelocations.exceptions.OfficeLocationNotFoundException;
import com.example.bgcpromogearreworked.persistence.entities.InventoryLevel;
import com.example.bgcpromogearreworked.persistence.entities.OfficeLocation;
import com.example.bgcpromogearreworked.persistence.repositories.InventoryLevelRepository;
import com.example.bgcpromogearreworked.persistence.repositories.OfficeLocationRepository;
import com.example.bgcpromogearreworked.persistence.repositories.ProductVariantRepository;
import com.example.bgcpromogearreworked.persistence.repositories.UserRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class OfficeLocationService {

    private final ProductVariantRepository variantRepo;
    private final OfficeLocationRepository locationRepo;
    private final InventoryLevelRepository inventoryRepo;

    public boolean checkOfficeLocationExists(Long locationId) {
        assert locationId != null;
        return locationRepo.existsById(locationId);
    }

    public OfficeLocation getOfficeLocation(Long locationId) {
        assert locationId != null;
        return locationRepo.findById(locationId).orElseThrow(OfficeLocationNotFoundException::new);
    }

    public List<OfficeLocation> getOfficeLocations() {
        return locationRepo.findAll();
    }

    public Page<OfficeLocation> getOfficeLocations(Predicate predicate, Pageable pageable) {
        assert predicate != null && pageable != null;
        return locationRepo.findAll(predicate, pageable);
    }

    private void createVariantsInventoryForNewOffice(OfficeLocation location) {
        assert location != null;
        variantRepo.findAll().forEach(variant -> {
            InventoryLevel inventoryLevel = new InventoryLevel();
            inventoryLevel.setLocationId(location.getId());
            inventoryLevel.setVariantId(variant.getId());
            inventoryRepo.saveAndFlush(inventoryLevel);
        });
    }

    @Transactional
    public <T> OfficeLocation createOfficeLocation(T source, Function<T, OfficeLocation> mapper) {
        assert source != null && mapper != null;
        OfficeLocation officeLocation = mapper.apply(source);
        assert officeLocation.getId() == null;
        officeLocation = locationRepo.saveAndFlush(officeLocation);
        createVariantsInventoryForNewOffice(officeLocation);
        return officeLocation;
    }

    public <T> OfficeLocation updateOfficeLocation(Long locationId, T source, BiFunction<T, OfficeLocation, OfficeLocation> mapper) {
        assert locationId != null && source != null & mapper != null;
        OfficeLocation officeLocation = mapper.apply(source, locationRepo.findById(locationId).orElseThrow(OfficeLocationNotFoundException::new));
        assert officeLocation.getId().equals(locationId);
        return locationRepo.saveAndFlush(officeLocation);
    }

    public void deleteOfficeLocation(Long locationId) {
        assert locationId != null;
        locationRepo.deleteById(locationId);
    }

}
