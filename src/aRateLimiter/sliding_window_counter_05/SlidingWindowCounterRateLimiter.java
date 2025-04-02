package aRateLimiter.sliding_window_counter_05;

import aRateLimiter.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowCounterRateLimiter implements RateLimiter {
    private final int limit; // The maximum number of requests allowed in the sliding window
    private final long windowSizeMillis; // The duration of the sliding window in milliseconds
    private final ConcurrentHashMap<Long, Integer> counter = new ConcurrentHashMap<>(); // Map to track request counts per time window

    // Constructor to initialize the rate limiter with a limit and window size
    public SlidingWindowCounterRateLimiter(int limit, long windowSizeMillis) {
        this.limit = limit; // Set the request limit
        this.windowSizeMillis = windowSizeMillis; // Set the window size duration
    }

    // Method to check if a request is allowed based on the sliding window counter logic
    @Override
    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis(); // Get the current timestamp in milliseconds

        // Determine the current window based on the timestamp divided by the window size
        long windowKey = now / windowSizeMillis;

        // Ensure there are entries for both the current and previous windows in the counter map
        counter.putIfAbsent(windowKey, 0); // Initialize current window counter if absent
        counter.putIfAbsent(windowKey - 1, 0); // Initialize previous window counter if absent

        // Fetch the request counts for the current and previous windows
        int currentCount = counter.get(windowKey);
        int previousCount = counter.get(windowKey - 1);

        // Compute a weighted count to smooth the transition between windows
        double previousWindowWeight = (double) (now % windowSizeMillis) / windowSizeMillis; // Percentage of time passed in current window
        double weightedCount = (previousCount * previousWindowWeight) + currentCount;

        // If the weighted request count is below the limit, allow the request and update the counter
        if (weightedCount < limit) {
            counter.put(windowKey, currentCount + 1); // Increment request count for the current window
            removeOldEntries(now); // Remove outdated window entries
            System.out.println("Accepted a request - Hitting the server API"); // ✅ API request is sent here
            return true;
        }

        // If the limit is exceeded, reject the request
        removeOldEntries(now); // Clean up old entries
        System.out.println("Dropped a request - Returning 429 Too Many Requests"); // 🚫 API is NOT hit, request is rejected
        return false;
    }

    // Method to remove outdated window entries from the counter map
    private void removeOldEntries(long now) {
        counter.keySet().removeIf(windowKey ->
                (now - windowKey * windowSizeMillis) > windowSizeMillis // Remove if window is older than allowed timeframe
        );
    }
}
