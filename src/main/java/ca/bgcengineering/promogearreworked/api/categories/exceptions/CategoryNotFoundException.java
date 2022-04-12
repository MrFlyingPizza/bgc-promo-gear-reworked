package ca.bgcengineering.promogearreworked.api.categories.exceptions;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException() {
        super("Category not found.");
    }

}
