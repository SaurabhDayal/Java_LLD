package behavioral.chainOfResponsibilityPattern.middlewarePkg;

// Middleware to enforce request rate limiting.
public class ThrottlingMiddleware extends Middleware {

    private int requestPerMinute; // Maximum allowed requests per minute.
    private int request; // Counter for requests within the current time period.
    private long currentTime; // Tracks the start of the current minute.

    // Constructor initializes the middleware with a request-per-minute limit.
    public ThrottlingMiddleware(int requestPerMinute) {
        this.requestPerMinute = requestPerMinute;
        this.currentTime = System.currentTimeMillis(); // Record the current time in milliseconds.
    }

    // Checks if the request rate exceeds the allowed limit.
    public boolean check(String email, String password) {

        // Reset the request counter if more than a minute has passed.
        if (System.currentTimeMillis() > currentTime + 60_000) {
            request = 0;
            currentTime = System.currentTimeMillis(); // Update the current time.
        }

        request++; // Increment the request counter.

        // If requests exceed the limit, display an error and stop the thread.
        if (request > requestPerMinute) {
            System.out.println("Request limit exceeded!");
            Thread.currentThread().stop(); // This is unsafe and deprecated; use alternative thread handling.
        }

        // Pass the request to the next middleware in the chain.
        return checkNext(email, password);
    }
}
