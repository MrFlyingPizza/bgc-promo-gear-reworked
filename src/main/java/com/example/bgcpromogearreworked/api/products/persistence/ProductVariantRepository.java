package com.example.bgcpromogearreworked.api.products.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
    boolean existsByIdAndProductId(long id, long productId);
}
