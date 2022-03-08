package com.example.bgcpromogearreworked.api.options.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionValueRepository extends JpaRepository<OptionValue, OptionValueId> {

    Iterable<OptionValue> findAllByName(String name);

}
