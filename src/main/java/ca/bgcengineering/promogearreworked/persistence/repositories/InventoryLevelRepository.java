package ca.bgcengineering.promogearreworked.persistence.repositories;

import ca.bgcengineering.promogearreworked.persistence.entities.InventoryLevel;
import ca.bgcengineering.promogearreworked.persistence.entities.InventoryLevelId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface InventoryLevelRepository extends JpaRepository<InventoryLevel, InventoryLevelId>, QuerydslPredicateExecutor<InventoryLevel> {

    Page<InventoryLevel> findAllByLocationId(Long locationId, Pageable pageable);

}
