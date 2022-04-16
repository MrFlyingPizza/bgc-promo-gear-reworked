package ca.bgcengineering.promogearreworked.persistence.repositories;

import ca.bgcengineering.promogearreworked.persistence.entities.OptionValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface OptionValueRepository extends JpaRepository<OptionValue, Long>, QuerydslPredicateExecutor<OptionValue> {

    List<OptionValue> findAllByOptionId(Long optionId);
    boolean existsByOptionIdAndValue(Long optionId, String value);
    boolean existsByOptionIdAndId(Long optionId, Long valueId);

}
