package ca.bgcengineering.promogearreworked.persistence.repositories;

import ca.bgcengineering.promogearreworked.persistence.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface TransferRepository extends JpaRepository<Transfer, Long>, QuerydslPredicateExecutor<Transfer> {
}