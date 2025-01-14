package behavioral.chainOfResponsibilityPattern.middlewarePkg;

import behavioral.chainOfResponsibilityPattern.serverPkg.Server;

// Middleware to check if the user exists and credentials are valid.
public class UserExistsMiddleware extends Middleware {

    private Server server; // Reference to the server to validate user details.

    // Constructor to initialize the middleware with a server instance.
    public UserExistsMiddleware(Server server) {
        this.server = server;
    }

    // Checks if the user exists and if the provided credentials are correct.
    public boolean check(String email, String password) {
        if (!server.hasEmail(email)) { // Check if the email is registered.
            System.out.println("This email is not registered!");
            return false; // Terminate the chain if email is not found.
        }
        if (!server.isValidPassword(email, password)) { // Validate the password.
            System.out.println("Wrong password!");
            return false; // Terminate the chain if the password is incorrect.
        }
        // Pass the request to the next middleware in the chain if it exists.
        return checkNext(email, password);
    }
}
