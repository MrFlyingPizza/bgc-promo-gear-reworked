package ca.bgcengineering.promogearreworked.api.products.exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException() {
        super("Product not found.");
    }
}
