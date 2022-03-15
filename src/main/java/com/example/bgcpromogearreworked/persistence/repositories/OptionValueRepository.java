package com.example.bgcpromogearreworked.persistence.repositories;

import com.example.bgcpromogearreworked.persistence.entities.OptionValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionValueRepository extends JpaRepository<OptionValue, Long> {

    Iterable<OptionValue> findAllByOptionId(Long optionId);
    boolean existsByOptionIdAndValue(Long optionId, String value);
    boolean existsByOptionIdAndId(Long optionId, Long valueId);

}
