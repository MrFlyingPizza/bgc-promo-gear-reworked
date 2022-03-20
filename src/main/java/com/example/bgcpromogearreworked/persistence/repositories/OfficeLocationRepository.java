package com.example.bgcpromogearreworked.persistence.repositories;

import com.example.bgcpromogearreworked.persistence.entities.OfficeLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface OfficeLocationRepository extends JpaRepository<OfficeLocation, Long>, QuerydslPredicateExecutor<OfficeLocation> {
}
