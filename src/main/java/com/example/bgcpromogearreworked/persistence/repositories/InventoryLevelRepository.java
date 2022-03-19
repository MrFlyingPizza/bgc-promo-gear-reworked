package com.example.bgcpromogearreworked.persistence.repositories;

import com.example.bgcpromogearreworked.persistence.entities.InventoryLevel;
import com.example.bgcpromogearreworked.persistence.entities.InventoryLevelId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface InventoryLevelRepository extends JpaRepository<InventoryLevel, InventoryLevelId>, QuerydslPredicateExecutor<InventoryLevel> {
}
