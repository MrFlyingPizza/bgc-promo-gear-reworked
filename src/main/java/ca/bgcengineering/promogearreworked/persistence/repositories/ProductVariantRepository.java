package ca.bgcengineering.promogearreworked.persistence.repositories;

import ca.bgcengineering.promogearreworked.persistence.entities.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long>, QuerydslPredicateExecutor<ProductVariant> {
    boolean existsByProductIdAndId(Long productId, Long id);
    boolean existsByIdAndIsInUse(Long variantId, Boolean isInUse);
    List<ProductVariant> findAllByProductId(Long productId);
}
