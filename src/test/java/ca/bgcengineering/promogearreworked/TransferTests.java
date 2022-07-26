package ca.bgcengineering.promogearreworked;

import ca.bgcengineering.promogearreworked.persistence.entities.*;
import ca.bgcengineering.promogearreworked.persistence.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class TransferTests {

    @Autowired
    TransferRepository transferRepo;

    @Autowired
    ProductVariantRepository variantRepo;

    @Autowired
    ProductRepository productRepo;

    @Autowired
    CategoryRepository categoryRepo;

    @Autowired
    OfficeLocationRepository officeLocationRepo;

    @Autowired
    InventoryLevelRepository inventoryRepo;

    @Test
    void saveTransfer() {

        final var testString = "transferTest";

        var origin = new OfficeLocation();
        origin.setName(testString);
        origin.setAddressLine1(testString);
        origin.setAddressLine2(testString);
        origin.setCity(testString);
        origin.setState(testString);
        origin.setCountry(testString);
        origin.setZipCode(testString);

        var destination = new OfficeLocation();
        destination.setName(testString);
        destination.setAddressLine1(testString);
        destination.setAddressLine2(testString);
        destination.setCity(testString);
        destination.setState(testString);
        destination.setCountry(testString);
        destination.setZipCode(testString);

        officeLocationRepo.saveAndFlush(origin);
        officeLocationRepo.saveAndFlush(destination);

        var category = new Category();
        category.setName(testString);

        categoryRepo.saveAndFlush(category);

        var product = new Product();
        product.setName(testString);
        product.setBrand(testString);
        product.setDescription(testString);
        product.setPrice(BigDecimal.ZERO);
        product.setIsBigItem(false);
        product.setIsPublished(false);
        product.setIsWaitListEnabled(false);
        product.setCategory(category);

        productRepo.saveAndFlush(product);

        var variant = new ProductVariant();
        variant.setProduct(product);
        variant.setWaitListThreshold(0);
        variant.setIsInUse(false);

        variantRepo.saveAndFlush(variant);

        var originInventory = new InventoryLevel();
        originInventory.setLocationId(origin.getId());
        originInventory.setVariantId(variant.getId());
        originInventory.setAvailableQuantity(10);

        var destinationInventory = new InventoryLevel();
        destinationInventory.setLocationId(destination.getId());
        destinationInventory.setVariantId(variant.getId());
        destinationInventory.setAvailableQuantity(0);

        inventoryRepo.saveAndFlush(originInventory);
        inventoryRepo.saveAndFlush(destinationInventory);

        var transfer = new Transfer();
        transfer.setVariant(variant);
        transfer.setDestination(destination);
        transfer.setOrigin(origin);
        transfer.setQuantity(1);
        var comments = "test test";
        transfer.setComments(comments);

        transferRepo.save(transfer);

        var newTransfer = transferRepo.findById(transfer.getId()).orElseThrow();

        assertNotNull(transfer);
        assertEquals(transfer.getVariant().getId(), variant.getId());
        assertEquals(transfer.getDestination().getId(), destination.getId());
        assertEquals(transfer.getOrigin().getId(), origin.getId());
        assertEquals(transfer.getQuantity(), newTransfer.getQuantity());
        assertEquals(transfer.getComments(), comments);
        assertEquals(newTransfer.getComments(), comments);
        assertEquals(transfer.getComments(), newTransfer.getComments());
    }

}
