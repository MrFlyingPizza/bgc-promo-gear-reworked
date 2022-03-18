package com.example.bgcpromogearreworked.persistence.repositories;

import com.example.bgcpromogearreworked.persistence.entities.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    boolean existsByProductIdAndId(Long productId, Long id);
    Streamable<ProductImage> findAllByProductId(Long productId);
}
