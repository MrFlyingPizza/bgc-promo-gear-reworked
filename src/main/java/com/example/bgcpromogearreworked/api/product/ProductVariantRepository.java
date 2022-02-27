package com.example.bgcpromogearreworked.api.product;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductVariantRepository extends PagingAndSortingRepository<ProductVariant, Long> {
    boolean existsByIdAndProductId(long id, long productId);
}
