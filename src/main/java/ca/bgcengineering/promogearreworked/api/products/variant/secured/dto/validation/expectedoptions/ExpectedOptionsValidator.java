package ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.validation.expectedoptions;

import ca.bgcengineering.promogearreworked.persistence.entities.Option;
import ca.bgcengineering.promogearreworked.persistence.entities.OptionValue;

import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class ExpectedOptionsValidator {

    // assumes that the list of optionValueIds contain IDs of existing OptionValues
    // assumes that the list of optionValueIds only has unique elements
    // assumes that product exists.
    protected static boolean validate(Set<OptionValue> optionValues, Set<Option> expectedOptions) {
        HashMap<Option, Boolean> optionsExpected = new HashMap<>();
        expectedOptions.forEach(option -> optionsExpected.put(option, false));
        Set<Option> givenOptions = optionValues.stream().map(OptionValue::getOption).collect(Collectors.toSet());
        givenOptions.forEach(option -> optionsExpected.computeIfPresent(option, (currentOption, present) -> true));
        return !optionsExpected.containsValue(false);
    }

}
