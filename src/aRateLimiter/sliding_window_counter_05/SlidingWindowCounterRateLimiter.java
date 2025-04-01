package aRateLimiter.sliding_window_counter_05;

import aRateLimiter.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowCounterRateLimiter implements RateLimiter {
    private final int limit;                            // The maximum number of requests allowed in the sliding window
    private final long windowSizeMillis;                // The duration of the sliding window in milliseconds
    private final ConcurrentHashMap<Long, Integer> counter = new ConcurrentHashMap<>(); // Concurrent map to track request counts for each window

    // Constructor to initialize the rate limiter with a limit and window size
    public SlidingWindowCounterRateLimiter(int limit, long windowSizeMillis) {
        this.limit = limit; // Set the limit for the rate limiter
        this.windowSizeMillis = windowSizeMillis; // Set the sliding window size in milliseconds
    }

    // Method to check if a request is allowed based on the current window's request count
    @Override
    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis(); // Get the current time in milliseconds

        // Calculate the window key based on the current time divided by the window size
        long windowKey = now / windowSizeMillis;

        // Ensure there are entries for the current window and the previous window in the map
        counter.putIfAbsent(windowKey, 0); // If no entry exists for the current window, initialize with 0 requests
        counter.putIfAbsent(windowKey - 1, 0); // If no entry exists for the previous window, initialize with 0 requests

        // Retrieve the request counts for the current and previous windows
        int currentCount = counter.get(windowKey); // Current window request count
        int previousCount = counter.get(windowKey - 1); // Previous window request count

        // Calculate the weighted count for the current sliding window:
        double previousWindowWeight = (double) (now % windowSizeMillis) / windowSizeMillis; // Fraction of the previous window's time
        double weightedCount = (previousCount * previousWindowWeight) + currentCount;

        // If the weighted request count is less than the limit, allow the request and increment the current window's count
        if (weightedCount < limit) {
            counter.put(windowKey, currentCount + 1); // Increment the current window's request count
            removeOldEntries(now); // Clean up old entries that are no longer relevant
            return true; // Request is allowed
        }

        // If the weighted request count exceeds the limit, deny the request
        removeOldEntries(now); // Clean up old entries that are no longer relevant
        return false;
    }

    // Method to clean up old entries in the counter map
    private void removeOldEntries(long now) {
        // Remove entries that are outside the sliding window
        counter.keySet().removeIf(windowKey ->
                // Check if the window is outdated by calculating the time difference between the current time and the window's start time
                (now - windowKey * windowSizeMillis) > windowSizeMillis // If the time elapsed since the window started exceeds the window size, remove it
        );
    }
}
