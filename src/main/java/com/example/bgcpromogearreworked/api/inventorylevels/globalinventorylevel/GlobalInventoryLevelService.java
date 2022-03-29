package com.example.bgcpromogearreworked.api.inventorylevels.globalinventorylevel;

import com.example.bgcpromogearreworked.api.inventorylevels.exceptions.GlobalInventoryLevelNotFound;
import com.example.bgcpromogearreworked.persistence.entities.GlobalInventoryLevel;
import com.example.bgcpromogearreworked.persistence.repositories.GlobalInventoryLevelRepository;
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
