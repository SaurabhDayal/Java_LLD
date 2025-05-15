package aRateLimiter.sliding_window_log_04;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class SlidingWindowLogRateLimiter {
    private final int limit;                                   // The maximum number of requests allowed in the sliding window
    private final long windowSizeMillis;                       // The duration of the sliding window in milliseconds
    private final Queue<Long> timestamps = new LinkedList<>(); // Queue to store timestamps of incoming requests

    // Constructor to initialize the rate limiter with a limit and window size
    public SlidingWindowLogRateLimiter(int limit, long windowSizeMillis) {
        this.limit = limit;                       // Set the limit for the rate limiter
        this.windowSizeMillis = windowSizeMillis; // Set the sliding window size in milliseconds
    }

    // Method to check if a request is allowed based on the current timestamps in the sliding window
    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis(); // Get the current time in milliseconds

        // Remove timestamps that are outside the current sliding window (older than the window size)
        while (!timestamps.isEmpty() && timestamps.peek() < now - windowSizeMillis) {
            timestamps.poll(); // Remove the oldest timestamp if it's outside the window
        }

        // If the number of requests in the window is less than the limit, allow the request
        if (timestamps.size() < limit) {
            timestamps.add(now); // Add the current timestamp to the queue
            System.out.println("✅ Accepted a request - Hitting the server API");
            return true;
        }

        // If the number of requests in the window exceeds the limit, deny the request
        System.out.println("🚫 Dropped a request - Returning 429 Too Many Requests");
        return false;
    }

    // aMachineCoding.designFileSystem.aMachineCoding.designRateLimiter.Main method for testing the Sliding Window Log Rate Limiter
    public static void main(String[] args) {
        SlidingWindowLogRateLimiter rateLimiter = new SlidingWindowLogRateLimiter(5, 1000); // 5 requests per 1 second
        Random random = new Random(); // Moved Random object inside main method
        System.out.println("Testing Sliding Window Log Rate Limiter...\n");

        for (int i = 0; i < 25; i++) {
            System.out.println("Request " + (i + 1) + " allowed? " + rateLimiter.allowRequest());
            try {
                // Sleep for a random time between 50ms and 250ms
                int sleepTimeMillis = 50 + random.nextInt(201); // Random between 50 and 250
                Thread.sleep(sleepTimeMillis);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
