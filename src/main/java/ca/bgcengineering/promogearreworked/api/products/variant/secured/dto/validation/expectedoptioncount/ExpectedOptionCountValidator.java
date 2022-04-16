package ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.validation.expectedoptioncount;

import ca.bgcengineering.promogearreworked.persistence.repositories.ProductRepository;

import java.util.List;

public abstract class ExpectedOptionCountValidator {

    // assuming optionValueIds and productIds not null
    protected static boolean validate(List<Long> optionValueIds, Long productId, ProductRepository productRepo) {
        return productRepo.findById(productId).orElseThrow().getOptions().size() == optionValueIds.size();
    }

}
