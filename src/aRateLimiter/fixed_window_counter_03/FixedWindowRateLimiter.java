package aRateLimiter.fixed_window_counter_03;

import aRateLimiter.RateLimiter;

import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowRateLimiter implements RateLimiter {
    private final int limit;              // The maximum number of requests allowed in the time window
    private final long windowSizeMillis;  // The duration of the time window in milliseconds
    private long windowStart;             // The start time of the current time window
    private final AtomicInteger count;    // Atomic counter to keep track of the number of requests in the current window

    // Constructor to initialize the rate limiter with a limit and window size
    public FixedWindowRateLimiter(int limit, long windowSizeMillis) {
        this.limit = limit;                            // Set the limit for the rate limiter
        this.windowSizeMillis = windowSizeMillis;      // Set the window size in milliseconds
        this.windowStart = System.currentTimeMillis(); // Set the window start to the current time
        this.count = new AtomicInteger(0);   // Initialize the counter to 0
    }

    // Method to check if a request is allowed based on the current count and window
    @Override
    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis(); // Get the current time in milliseconds

        // If the current time has surpassed the window duration, reset the counter
        if (now - windowStart >= windowSizeMillis) {
            windowStart = now; // Reset the window start time to the current time
            count.set(0); // Reset the request counter to 0
        }

        // Increment the counter and check if the request is within the allowed limit
        if (count.incrementAndGet() <= limit) {
            return true; // If the counter is within the limit, allow the request
        }

        // If the limit is exceeded, deny the request
        return false;
    }
}
