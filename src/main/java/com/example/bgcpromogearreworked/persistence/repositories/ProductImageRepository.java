package com.example.bgcpromogearreworked.persistence.repositories;

import com.example.bgcpromogearreworked.persistence.entities.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    boolean existsByProductIdAndId(Long productId, Long id);
    Iterable<ProductImage> findAllByProductId(Long productId);
}
