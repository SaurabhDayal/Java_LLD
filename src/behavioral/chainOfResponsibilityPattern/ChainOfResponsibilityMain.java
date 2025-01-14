package behavioral.chainOfResponsibilityPattern;

import behavioral.chainOfResponsibilityPattern.middlewarePkg.Middleware;
import behavioral.chainOfResponsibilityPattern.middlewarePkg.RoleCheckMiddleware;
import behavioral.chainOfResponsibilityPattern.middlewarePkg.ThrottlingMiddleware;
import behavioral.chainOfResponsibilityPattern.middlewarePkg.UserExistsMiddleware;
import behavioral.chainOfResponsibilityPattern.serverPkg.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChainOfResponsibilityMain {

    private static BufferedReader reader;
    private static Server server;

    public static void main(String[] args) throws IOException {

        initServer();                                                  // Initialize the server and middleware chain.
        reader = new BufferedReader(new InputStreamReader(System.in)); // Setup buffered reader for reading user input

        boolean success;
        do {
            // Get email and password input from the user
            System.out.print("Enter email: ");
            String email = reader.readLine().trim(); // Remove whitespace and newline characters.
            System.out.print("Input password: ");
            String password = reader.readLine().trim(); // Remove whitespace and newline characters.

            // Attempting to log in with the provided email and password.
            success = server.logIn(email, password);
        } while (!success); // Repeat until successful login.
    }

    private static void initServer() {

        server = new Server();
        // Registering users with email and password in the server.
        server.register("admin@", "admin");
        server.register("user@", "user");

        // Setting up the middleware chain.
        Middleware middleware = Middleware.link(
                new ThrottlingMiddleware(2), // Limit the number of requests per minute.
                new UserExistsMiddleware(server), // Check if the user exists in the server.
                new RoleCheckMiddleware() // Verify the role of the user (e.g., admin or regular user).
        );

        // Assigning the middleware chain to the server.
        server.setMiddleware(middleware);
    }
}
