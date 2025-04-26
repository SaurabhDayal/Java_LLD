package aRateLimiter.fixed_window_counter_03;

public class FixedWindowRateLimiter {
    private final int limit;              // The maximum number of requests allowed in the time window
    private final long windowSizeMillis;  // The duration of the time window in milliseconds
    private long windowStart;             // The start time of the current time window
    private int count;                    // Counter to keep track of the number of requests in the current window

    // Constructor to initialize the rate limiter with a limit and window size
    public FixedWindowRateLimiter(int limit, long windowSizeMillis) {
        this.limit = limit;
        this.windowSizeMillis = windowSizeMillis;
        this.windowStart = System.currentTimeMillis();
        this.count = 0;
    }

    // Check if request can be allowed based on the current window
    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis(); // Get the current time in milliseconds

        // If the current time has surpassed the window duration, reset the counter
        if (now - windowStart >= windowSizeMillis) {
            windowStart = now; // Reset the window start time to the current time
            count = 0; // Reset the request counter to 0
        }

        // Increment the counter and check if the request is within the allowed limit
        if (count < limit) {
            count++; // Increment the request counter
            System.out.println("✅ Accepted a request - Hitting the server API"); // API request is sent here
            return true;
        }

        // If the limit is exceeded, deny the request
        System.out.println("🚫 Dropped a request - Returning 429 Too Many Requests"); // API is NOT hit, request is rejected
        return false;
    }

    // Main method for testing the Fixed Window Rate Limiter
    public static void main(String[] args) {
        FixedWindowRateLimiter rateLimiter = new FixedWindowRateLimiter(5, 2000); // Limit: 5 requests per 2 second
        System.out.println("Testing Fixed Window Rate Limiter...\n");

        for (int i = 0; i < 25; i++) {
            System.out.println("Request " + (i + 1) + " allowed? " + rateLimiter.allowRequest());
            try {
                Thread.sleep(200); // Sleep for 200ms between requests
            } catch (InterruptedException ignored) {
            }
        }
    }
}
