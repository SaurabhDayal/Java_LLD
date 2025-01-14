package behavioral.chainOfResponsibilityPattern.middlewarePkg;

// Abstract class representing a middleware component in the chain of responsibility.
public abstract class Middleware {

    private Middleware next; // Reference to the next middleware in the chain.

    // Static method to link multiple middleware components into a chain.
    public static Middleware link(Middleware first, Middleware... chain) {
        Middleware head = first;
        for (Middleware nextInChain : chain) { // Iterate through the chain and link each component.
            head.next = nextInChain;
            head = nextInChain;
        }
        return first; // Return the first middleware in the chain.
    }

    // Abstract method to be implemented by concrete middleware to perform specific checks.
    public abstract boolean check(String email, String password);

    // Passes the request to the next middleware in the chain, if it exists.
    protected boolean checkNext(String email, String password) {
        if (next == null) { // If no next middleware, return true to end the chain.
            return true;
        }
        return next.check(email, password); // Delegate to the next middleware.
    }
}
