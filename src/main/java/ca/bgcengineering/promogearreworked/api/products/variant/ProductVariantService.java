package ca.bgcengineering.promogearreworked.api.products.variant;

import ca.bgcengineering.promogearreworked.api.products.exceptions.ProductVariantNotFoundException;
import ca.bgcengineering.promogearreworked.persistence.entities.InventoryLevel;
import ca.bgcengineering.promogearreworked.persistence.entities.OfficeLocation;
import ca.bgcengineering.promogearreworked.persistence.entities.ProductVariant;
import ca.bgcengineering.promogearreworked.persistence.repositories.InventoryLevelRepository;
import ca.bgcengineering.promogearreworked.persistence.repositories.OfficeLocationRepository;
import ca.bgcengineering.promogearreworked.persistence.repositories.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;


@Service
@RequiredArgsConstructor
public class ProductVariantService {

    private final ProductVariantRepository variantRepo;
    private final InventoryLevelRepository inventoryLevelRepo;
    private final OfficeLocationRepository officeLocationRepo;

    public boolean checkProductVariantExists(Long variantId) {
        assert variantId != null;
        return variantRepo.existsById(variantId);
    }

    public boolean checkProductVariantExistsOnProduct(Long productId, Long variantId) {
        assert productId != null && variantId != null;
        return variantRepo.existsByProductIdAndId(productId, variantId);
    }

    public ProductVariantAvailability getProductVariantAvailability(Long variantId) {
        assert variantId != null;
        ProductVariant variant = variantRepo.getById(variantId);
        int apparentQuantity = variant.getGlobalInventoryLevel().getApparentQuantity();
        ProductVariantAvailability availability;
        if (apparentQuantity > 0) {
            availability = ProductVariantAvailability.AVAILABLE;
        } else if (variant.getProduct().getIsWaitListEnabled()) {
            availability = ProductVariantAvailability.WAIT_LIST;
        } else {
            availability = ProductVariantAvailability.UNAVAILABLE;
        }
        return availability;
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
