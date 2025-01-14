package behavioral.chainOfResponsibilityPattern.serverPkg;

import behavioral.chainOfResponsibilityPattern.middlewarePkg.Middleware;

import java.util.HashMap;
import java.util.Map;

public class Server {
    
    // A map to store registered users with their email as the key and password as the value.
    private Map<String, String> users = new HashMap<>();
    // Middleware chain that handles request validation.
    private Middleware middleware;

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
