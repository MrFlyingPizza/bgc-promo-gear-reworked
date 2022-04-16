package ca.bgcengineering.promogearreworked.persistence.repositories;

import ca.bgcengineering.promogearreworked.persistence.entities.OfficeLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface OfficeLocationRepository extends JpaRepository<OfficeLocation, Long>, QuerydslPredicateExecutor<OfficeLocation> {

    boolean existsByName(String name);

}
