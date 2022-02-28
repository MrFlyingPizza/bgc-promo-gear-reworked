package com.example.bgcpromogearreworked.api.products.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductVariantRepository extends PagingAndSortingRepository<ProductVariant, Long> {
    boolean existsByIdAndProductId(long id, long productId);
}
