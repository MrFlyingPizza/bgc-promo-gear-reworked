package ca.bgcengineering.promogearreworked.api.inventorylevels.inventorylevel;

import ca.bgcengineering.promogearreworked.api.inventorylevels.exceptions.InventoryLevelNotFoundException;
import ca.bgcengineering.promogearreworked.persistence.entities.InventoryLevel;
import ca.bgcengineering.promogearreworked.persistence.entities.InventoryLevelId;
import ca.bgcengineering.promogearreworked.persistence.repositories.InventoryLevelRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.BiFunction;

@Service
@RequiredArgsConstructor
public class InventoryLevelService {

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
    public Page<InventoryLevel> getInventoryLevels(Long locationId, Pageable pageable) {
        assert locationId != null && pageable != null;
        return inventoryLevelRepo.findAllByLocationId(locationId, pageable);
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
        return inventoryLevelRepo.saveAndFlush(level);
    }

}
