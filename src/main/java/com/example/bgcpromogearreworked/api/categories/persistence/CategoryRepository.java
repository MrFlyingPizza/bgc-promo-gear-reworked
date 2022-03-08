package com.example.bgcpromogearreworked.api.categories.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByNameAndParentId(String name, Long parentId);

}
