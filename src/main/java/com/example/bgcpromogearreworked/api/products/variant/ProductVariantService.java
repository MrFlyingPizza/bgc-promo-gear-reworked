package com.example.bgcpromogearreworked.api.products.variant;

import com.example.bgcpromogearreworked.api.products.exceptions.ProductVariantNotFoundException;
import com.example.bgcpromogearreworked.persistence.entities.*;
import com.example.bgcpromogearreworked.persistence.repositories.InventoryLevelRepository;
import com.example.bgcpromogearreworked.persistence.repositories.OfficeLocationRepository;
import com.example.bgcpromogearreworked.persistence.repositories.ProductRepository;
import com.example.bgcpromogearreworked.persistence.repositories.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductVariantService {

    private final ProductVariantRepository variantRepo;
    private final ProductRepository productRepo;
    private final InventoryLevelRepository inventoryLevelRepo;
    private final OfficeLocationRepository officeLocationRepo;

    public boolean checkProductVariantExistsOnProduct(Long productId, Long variantId) {
        assert productId != null && variantId != null;
        return variantRepo.existsByProductIdAndId(productId, variantId);
    }

    private boolean checkProductVariantHasExpectedOptions(ProductVariant variant) {
        assert variant != null;
        assert variant.getProduct() != null;
        assert variant.getOptionValues() != null;
        Set<Option> expectedOptions = variant.getProduct().getOptions();
        Set<Option> givenOptions = variant.getOptionValues().stream().map(OptionValue::getOption).collect(Collectors.toSet());
        return expectedOptions.equals(givenOptions);
    }

    private boolean checkProductVariantOptionsValid(ProductVariant productVariant) {
        assert productVariant != null;
        return checkProductVariantHasExpectedOptions(productVariant);
    }

    public ProductVariant getProductVariant(Long variantId) {
        assert variantId != null;
        return variantRepo.findById(variantId).orElseThrow(ProductVariantNotFoundException::new);
    }

    public List<ProductVariant> getProductVariants(Long productId) {
        assert productId != null;
        return variantRepo.findAllByProductId(productId);
    }

    private void createInventoryLevelsForVariant(Long variantId) {
        List<OfficeLocation> officeLocations = officeLocationRepo.findAll();
        for (OfficeLocation location : officeLocations) {
            InventoryLevel level = new InventoryLevel();
            level.setVariantId(variantId);
            level.setLocationId(location.getId());
            level.setAvailableQuantity(0);
            level.setReservedQuantity(0);
            level.setNotifyThreshold(0); // TODO: 2022-03-20 look into configs for default this value
            inventoryLevelRepo.saveAndFlush(level);
        }
    }

    public <T> ProductVariant createProductVariant(T source, Function<T, ProductVariant> mapper) {
        assert source != null && mapper != null;
        ProductVariant variant = mapper.apply(source);
        assert variant.getId() == null;
        variant = variantRepo.saveAndFlush(variant);
        createInventoryLevelsForVariant(variant.getId());
        return variant;
    }

    public <T> ProductVariant updateProductVariant(Long variantId, T source, BiFunction<T, ProductVariant, ProductVariant> mapper) {
        assert variantId != null && source != null && mapper != null;
        ProductVariant updated = mapper.apply(source, variantRepo.findById(variantId).orElseThrow(ProductVariantNotFoundException::new));
        assert updated.getId().equals(variantId);
        return variantRepo.saveAndFlush(updated);
    }

    public void deleteProductVariant(Long variantId) {
        assert variantId != null;
        variantRepo.deleteById(variantId);
    }
}
