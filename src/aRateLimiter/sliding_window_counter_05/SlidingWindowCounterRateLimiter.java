package aRateLimiter.sliding_window_counter_05;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowCounterRateLimiter {
    private final int limit;                                // The maximum number of requests allowed in the sliding window
    private final long windowSizeMillis;                    // The duration of the sliding window in milliseconds
    private final ConcurrentHashMap<Long, Integer> counter; // Map to track request counts per time window

    // Constructor to initialize the rate limiter with a limit and window size
    public SlidingWindowCounterRateLimiter(int limit, long windowSizeMillis) {
        this.limit = limit;
        this.windowSizeMillis = windowSizeMillis;
        this.counter = new ConcurrentHashMap<>();
    }

    // Method to allow or reject requests based on the sliding window
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
        double currentWindowWeight = (double) (now % windowSizeMillis) / windowSizeMillis;
        double previousWindowWeight = 1 - currentWindowWeight; // Weight for the previous window

        // Calculate the weighted previous count
        double weightedPreviousCount = previousCount * previousWindowWeight;

        // Total request count is the weighted previous count plus the current count
        double totalCount = weightedPreviousCount + currentCount;

        // If the total count is below the limit, allow the request
        if (totalCount < limit) {
            counter.put(windowKey, currentCount + 1); // Increment the current window counter
            removeOldEntries(now); // Clean up outdated window entries
            System.out.println("✅ Accepted a request - Hitting the server API");
            return true;
        }

        // If the request exceeds the limit, reject it
        removeOldEntries(now); // Clean up old entries
        System.out.println("🚫 Dropped a request - Returning 429 Too Many Requests");
        return false;
    }

    // Method to remove old window entries from the counter map
    private void removeOldEntries(long now) {
        // Determine the current window bucket by dividing the current time by window size
        long currentWindowKey = now / windowSizeMillis;

        // Iterate through the counter map and remove windows older than the current window
        for (Long key : counter.keySet()) {
            // If the key is older than the current window, remove it
            if (key < currentWindowKey - 1) {
                counter.remove(key);
            }
        }
    }
    
    public static void main(String[] args) {
        SlidingWindowCounterRateLimiter rateLimiter = new SlidingWindowCounterRateLimiter(5, 1000); // Limit: 5 requests, Window Size: 1 second
        Random random = new Random();
        System.out.println("Testing Sliding Window Counter Rate Limiter...\n");

        for (int i = 0; i < 25; i++) {
            System.out.println("Request " + (i + 1) + " allowed? " + rateLimiter.allowRequest());
            try {
                // Simulate a random delay between 50ms and 250ms
                int sleepTime = 50 + random.nextInt(201);
                Thread.sleep(sleepTime);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
