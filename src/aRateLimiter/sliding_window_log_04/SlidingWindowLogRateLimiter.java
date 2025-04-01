package aRateLimiter.sliding_window_log_04;

import aRateLimiter.RateLimiter;

import java.util.LinkedList;
import java.util.Queue;

public class SlidingWindowLogRateLimiter implements RateLimiter {
    private final int limit;                                   // The maximum number of requests allowed in the sliding window
    private final long windowSizeMillis;                       // The duration of the sliding window in milliseconds
    private final Queue<Long> timestamps = new LinkedList<>(); // Queue to store timestamps of incoming requests

    // Constructor to initialize the rate limiter with a limit and window size
    public SlidingWindowLogRateLimiter(int limit, long windowSizeMillis) {
        this.limit = limit;                       // Set the limit for the rate limiter
        this.windowSizeMillis = windowSizeMillis; // Set the sliding window size in milliseconds
    }

    // Method to check if a request is allowed based on the current timestamps in the sliding window
    @Override
    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis(); // Get the current time in milliseconds

        // Remove timestamps that are outside the current sliding window (older than the window size)
        while (!timestamps.isEmpty() && timestamps.peek() < now - windowSizeMillis) {
            timestamps.poll(); // Remove the oldest timestamp if it's outside the window
        }

        // If the number of requests in the window is less than the limit, allow the request
        if (timestamps.size() < limit) {
            timestamps.add(now); // Add the current timestamp to the queue
            return true; // Allow the request
        }

        // If the number of requests in the window exceeds the limit, deny the request
        return false;
    }
}
