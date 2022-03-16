package com.example.bgcpromogearreworked.persistence.repositories;

import com.example.bgcpromogearreworked.persistence.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // TODO: 2022-03-11 fix saveAndFlush no long returns the save entity
    boolean existsByNameAndParentId(String name, Long parentId);

}
