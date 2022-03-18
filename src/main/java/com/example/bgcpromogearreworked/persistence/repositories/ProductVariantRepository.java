package com.example.bgcpromogearreworked.persistence.repositories;

import com.example.bgcpromogearreworked.persistence.entities.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
    boolean existsByProductIdAndId(Long productId, Long id);
    Streamable<ProductVariant> findAllByProductId(Long productId);
}
