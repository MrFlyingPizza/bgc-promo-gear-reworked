package ca.bgcengineering.promogearreworked.api.users.exceptions;

public class GraphUserNotFoundException extends RuntimeException {
    public GraphUserNotFoundException() {
        super("Graph user not found.");
    }
}
