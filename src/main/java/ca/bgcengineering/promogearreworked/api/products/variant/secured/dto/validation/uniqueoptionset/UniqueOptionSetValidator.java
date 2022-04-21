package ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.validation.uniqueoptionset;

import ca.bgcengineering.promogearreworked.persistence.entities.OptionValue;
import ca.bgcengineering.promogearreworked.persistence.entities.Product;
import ca.bgcengineering.promogearreworked.persistence.repositories.ProductRepository;
import ca.bgcengineering.promogearreworked.persistence.entities.ProductVariant;
import ca.bgcengineering.promogearreworked.persistence.repositories.OptionValueRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class UniqueOptionSetValidator {

    // assumes that there are the same number of option values as option that were specified for the product.
    protected static boolean validate(Set<OptionValue> candidateValueSet, List<Set<OptionValue>> existingValueSets) {
        for (Set<OptionValue> existingValueSet : existingValueSets) {
            if (candidateValueSet.equals(existingValueSet)) {
                return false;
            }
        }
        return true;
    }

}
