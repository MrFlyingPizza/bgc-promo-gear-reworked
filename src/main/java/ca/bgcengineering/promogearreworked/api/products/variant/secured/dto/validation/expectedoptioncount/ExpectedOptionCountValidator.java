package ca.bgcengineering.promogearreworked.api.products.variant.secured.dto.validation.expectedoptioncount;

public abstract class ExpectedOptionCountValidator {

    // assuming optionValueIds and productIds not null
    protected static boolean validate(int valueSetSize, int optionSetSize) {
        return optionSetSize == valueSetSize;
    }

}
