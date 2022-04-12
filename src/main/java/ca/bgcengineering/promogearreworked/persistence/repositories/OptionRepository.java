package ca.bgcengineering.promogearreworked.persistence.repositories;

import ca.bgcengineering.promogearreworked.persistence.entities.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface OptionRepository extends JpaRepository<Option, Long>, QuerydslPredicateExecutor<Option> {

    boolean existsByName(String name);

}
