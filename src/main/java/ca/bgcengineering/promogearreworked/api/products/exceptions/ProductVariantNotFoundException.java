package ca.bgcengineering.promogearreworked.api.products.exceptions;

public class ProductVariantNotFoundException extends RuntimeException {

    public ProductVariantNotFoundException() {
        super("Product variant not found.");
    }

}
