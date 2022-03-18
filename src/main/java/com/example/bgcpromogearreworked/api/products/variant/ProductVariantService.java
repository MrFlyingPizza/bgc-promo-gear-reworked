package com.example.bgcpromogearreworked.api.products.variant;

import com.example.bgcpromogearreworked.api.products.exceptions.ProductVariantNotFoundException;
import com.example.bgcpromogearreworked.persistence.entities.ProductVariant;
import com.example.bgcpromogearreworked.persistence.repositories.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;
import java.util.function.Function;


@Service
@RequiredArgsConstructor
public class ProductVariantService {

    private final ProductVariantRepository variantRepo;

    public boolean checkProductVariantExists(Long variantId) {
        return variantRepo.existsById(variantId);
    }
    public boolean checkProductVariantExistsOnProduct(Long productId, Long variantId) {
        return variantRepo.existsByProductIdAndId(productId, variantId);
    }

    public ProductVariant getProductVariant(Long variantId) throws ProductVariantNotFoundException {
        return variantRepo.findById(variantId).orElseThrow(ProductVariantNotFoundException::new);
    }

    public Streamable<ProductVariant> getProductVariants(Long productId) {
        return variantRepo.findAllByProductId(productId);
    }

    public ProductVariant createProductVariant(ProductVariant variant) {
        if (variant.getId() != null) {
            throw new RuntimeException("Product variant ID must be null.");
        }
        return variantRepo.saveAndFlush(variant);
    }

    public <T> ProductVariant createProductVariant(T source, Function<T, ProductVariant> mapper) {
        ProductVariant variant = mapper.apply(source);
        if (variant.getId() != null) {
            throw new RuntimeException("Product variant ID must be null.");
        }
        return variantRepo.saveAndFlush(variant);
    }

    public ProductVariant updateProductVariant(ProductVariant variant) {
        if (variant.getId() == null) {
            throw new RuntimeException("Product variant ID must not be null.");
        }
        return variantRepo.saveAndFlush(variant);
    }

    public <T> ProductVariant updateProductVariant(Long variantId, T source, BiFunction<T, ProductVariant, ProductVariant> mapper) {
        ProductVariant variant = mapper.apply(source, variantRepo.findById(variantId).orElseThrow(ProductVariantNotFoundException::new));
        if (!variant.getId().equals(variantId)) {
            throw new RuntimeException("Mapper function is forbidden to modify product variant ID.");
        }
        return variantRepo.saveAndFlush(variant);
    }

    public void deleteProductVariant(Long variantId) {
        variantRepo.deleteById(variantId);
    }

    public void deleteProductVariant(ProductVariant variant) {
        variantRepo.delete(variant);
    }

}
