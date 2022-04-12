package ca.bgcengineering.promogearreworked.persistence.repositories;

import ca.bgcengineering.promogearreworked.persistence.entities.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long>, QuerydslPredicateExecutor<ProductImage> {

    boolean existsByProductIdAndId(Long productId, Long id);
    List<ProductImage> findAllByProductId(Long productId);
}
