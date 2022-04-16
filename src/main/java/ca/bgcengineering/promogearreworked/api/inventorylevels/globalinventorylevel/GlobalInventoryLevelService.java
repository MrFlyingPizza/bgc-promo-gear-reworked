package ca.bgcengineering.promogearreworked.api.inventorylevels.globalinventorylevel;

import ca.bgcengineering.promogearreworked.api.inventorylevels.exceptions.GlobalInventoryLevelNotFound;
import ca.bgcengineering.promogearreworked.persistence.entities.GlobalInventoryLevel;
import ca.bgcengineering.promogearreworked.persistence.repositories.GlobalInventoryLevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GlobalInventoryLevelService {

    private final GlobalInventoryLevelRepository repo;

    public GlobalInventoryLevel getGlobalInventoryLevel(Long variantId) {
        assert variantId != null;
        return repo.findById(variantId).orElseThrow(GlobalInventoryLevelNotFound::new);
    }

    public List<GlobalInventoryLevel> getGlobalInventoryLevels() {
        return repo.findAll();
    }

}
