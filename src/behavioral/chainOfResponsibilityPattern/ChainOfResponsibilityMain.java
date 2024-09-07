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


    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Server server;

    private static void init() {
        server = new Server();
        server.register("admin@", "admin");
        server.register("user@", "user");

        // All checks are linked. Client can build various chains using the same components.
        Middleware middleware = Middleware.link(
                new ThrottlingMiddleware(2),
                new UserExistsMiddleware(server),
                new RoleCheckMiddleware()
        );

        // Server gets a chain from client code.
        server.setMiddleware(middleware);
    }

    public static void main(String[] args) throws IOException {

        init();

        boolean success;
        do {
            System.out.print("Enter email: ");
            String email = reader.readLine();
            System.out.print("Input password: ");
            String password = reader.readLine();
            success = server.logIn(email, password);
        } while (!success);
    }

}
