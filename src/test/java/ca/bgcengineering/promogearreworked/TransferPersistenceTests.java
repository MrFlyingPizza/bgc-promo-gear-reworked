package ca.bgcengineering.promogearreworked;

import ca.bgcengineering.promogearreworked.persistence.entities.*;
import ca.bgcengineering.promogearreworked.persistence.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test")
public class TransferPersistenceTests {

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

        var category = new Category();
        category.setName(testString);

        var product = new Product();
        product.setName(testString);
        product.setBrand(testString);
        product.setDescription(testString);
        product.setPrice(BigDecimal.ZERO);
        product.setIsBigItem(false);
        product.setIsPublished(false);
        product.setIsWaitListEnabled(false);
        product.setCategory(category);

        var variant = new ProductVariant();
        variant.setProduct(product);
        variant.setWaitListThreshold(0);
        variant.setIsInUse(false);

        var transfer = new Transfer();

        transfer.setOrigin(origin);
        transfer.setDestination(destination);
        transfer.setQuantity(1);
        transfer.setVariant(variant);

        categoryRepo.save(category);

        productRepo.save(product);

        variantRepo.save(variant);

        officeLocationRepo.saveAll(List.of(origin, destination));

        transferRepo.save(transfer);

        assertNotNull(transferRepo.getReferenceById(transfer.getId()));
    }

}
