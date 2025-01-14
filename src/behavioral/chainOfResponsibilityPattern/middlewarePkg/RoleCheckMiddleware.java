package behavioral.chainOfResponsibilityPattern.middlewarePkg;

// Concrete middleware that checks the user's role based on the email address.
public class RoleCheckMiddleware extends Middleware {

    // Checks the user's role. If the email is "admin@", the user is treated as an admin.
    public boolean check(String email, String password) {

        if (email.equals("admin@")) { // Check if the email belongs to an admin.
            System.out.println("Hello, admin!"); // Admin specific greeting.
            return true; // Admins are successfully authorized.
        }

        System.out.println("Hello, user!"); // Regular user greeting.

        // Pass the request to the next middleware in the chain if it exists.
        return checkNext(email, password);
    }
}
