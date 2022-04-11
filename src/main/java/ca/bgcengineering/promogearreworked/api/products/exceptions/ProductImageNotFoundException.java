package ca.bgcengineering.promogearreworked.api.products.exceptions;

public class ProductImageNotFoundException extends RuntimeException {

    public ProductImageNotFoundException() {
        super("Product image not found.");
    }
}
