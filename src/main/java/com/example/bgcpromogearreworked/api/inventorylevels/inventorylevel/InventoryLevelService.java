package com.example.bgcpromogearreworked.api.inventorylevels.inventorylevel;

import com.example.bgcpromogearreworked.api.inventorylevels.exceptions.InventoryLevelNotFoundException;
import com.example.bgcpromogearreworked.persistence.entities.InventoryLevel;
import com.example.bgcpromogearreworked.persistence.entities.InventoryLevelId;
import com.example.bgcpromogearreworked.persistence.repositories.InventoryLevelRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.BiFunction;

@Service
@RequiredArgsConstructor
public class InventoryLevelService { // TODO: 2022-03-26 make inventory levels a lower domain object of locations

    private final InventoryLevelRepository inventoryLevelRepo;

    public boolean checkInventoryLevelExists(Long locationId, Long variantId) {
        assert locationId != null && variantId != null;
        return inventoryLevelRepo.existsById(new InventoryLevelId(locationId, variantId));
    }

    @Transactional
    public InventoryLevel getInventoryLevel(Long locationId, Long variantId) {
        assert locationId != null && variantId != null;
        return inventoryLevelRepo.findById(new InventoryLevelId(locationId, variantId)).orElseThrow();
    }

    @Transactional
    public List<InventoryLevel> getInventoryLevels() {
        return inventoryLevelRepo.findAll();
    }

    @Transactional
    public Page<InventoryLevel> getInventoryLevels(Predicate predicate, Pageable pageable) {
        assert predicate != null && pageable != null;
        return inventoryLevelRepo.findAll(predicate, pageable);
    }

    @Transactional
    public <T> InventoryLevel updateInventoryLevel(Long locationId,
                                                   Long variantId,
                                                   T source,
                                                   BiFunction<T, InventoryLevel, InventoryLevel> mapper) {
        assert locationId != null && variantId != null && source != null && mapper != null;
        InventoryLevel level = mapper.apply(source, inventoryLevelRepo.findById(
                new InventoryLevelId(locationId, variantId)).orElseThrow(InventoryLevelNotFoundException::new));
        assert level.getLocationId().equals(locationId) && level.getVariantId().equals(variantId);
        return inventoryLevelRepo.saveAndFlush(level); // TODO: 2022-03-23 put in last manually updated user
    }

}
