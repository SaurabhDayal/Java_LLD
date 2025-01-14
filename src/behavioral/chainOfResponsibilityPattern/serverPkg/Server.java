package behavioral.chainOfResponsibilityPattern.serverPkg;

import behavioral.chainOfResponsibilityPattern.middlewarePkg.Middleware;

import java.util.HashMap;
import java.util.Map;

public class Server {

    private Map<String, String> users;  // Map to store registered users (email -> password).
    private Middleware middleware;      // Middleware chain to handle authentication.

    // Constructor to initialize the server instance with an empty user map.
    public Server() {
        this.users = new HashMap<>(); // Explicitly initialize the user map.
    }

    // Sets the middleware chain for the server.
    public void setMiddleware(Middleware middleware) {
        this.middleware = middleware;
    }

    // Handles user login by passing email and password through the middleware chain.
    public boolean logIn(String email, String password) {
        if (middleware.check(email, password)) { // Checks the chain of responsibilities.
            System.out.println("Authorization have been successful!");
            // Perform operations for authenticated users.
            return true;
        }
        return false; // Authorization failed.
    }

    // Registers a new user with the provided email and password.
    public void register(String email, String password) {
        users.put(email, password);
    }

    // Checks if a user exists with the given email.
    public boolean hasEmail(String email) {
        return users.containsKey(email);
    }

    // Validates if the given password matches the one registered for the email.
    public boolean isValidPassword(String email, String password) {
        return users.get(email).equals(password);
    }
}
