package aRateLimiter.fixed_window_counter_03;

import java.util.Random;

public class FixedWindowCounterRateLimiter {
    private final int limit;              // Maximum number of requests allowed in the time window
    private long currentWindowStart;      // Start time of the current window (aligned to window boundaries)
    private final long windowSizeMillis;  // Duration of the time window in milliseconds
    private int count;                    // Counter for requests in the current window

    // Constructor to initialize the rate limiter with a limit and window size
    public FixedWindowCounterRateLimiter(int limit, long windowSizeMillis) {
        this.limit = limit;
        this.currentWindowStart = alignToWindow(System.currentTimeMillis());
        this.windowSizeMillis = windowSizeMillis;
        this.count = 0;
    }

    // Align the given time to the start of its window
    private long alignToWindow(long timeMillis) {
        return (timeMillis / windowSizeMillis) * windowSizeMillis;
    }

    // Check if a request is allowed based on the current window
    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis();
        long alignedWindowStart = alignToWindow(now);

        // If moved to a new window, reset the counter
        if (alignedWindowStart != currentWindowStart) {
            currentWindowStart = alignedWindowStart;
            count = 0;
        }

        // Allow request if within the limit
        if (count < limit) {
            count++;
            System.out.println("✅ Accepted a request - Hitting the server API");
            return true;
        }

        // Deny request if limit exceeded
        System.out.println("🚫 Dropped a request - Returning 429 Too Many Requests");
        return false;
    }

    // aMachineCoding.designFileSystem.aMachineCoding.designRateLimiter.Main method for testing the Fixed Window Rate Limiter
    public static void main(String[] args) {
        FixedWindowCounterRateLimiter rateLimiter = new FixedWindowCounterRateLimiter(5, 2000); // 5 requests per 2 seconds
        Random random = new Random(); // Create a Random object for generating random sleep times
        System.out.println("Testing Fixed Window Counter Rate Limiter...\n");

        for (int i = 0; i < 25; i++) {
            System.out.println("Request " + (i + 1) + " allowed? " + rateLimiter.allowRequest());
            try {
                // Sleep for a random time between 50ms and 500ms
                int sleepTimeMillis = 50 + random.nextInt(451); // Random number between 50 and 500
                Thread.sleep(sleepTimeMillis);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
