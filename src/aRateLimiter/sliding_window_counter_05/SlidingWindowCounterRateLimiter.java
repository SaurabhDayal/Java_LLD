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
            System.out.println("✅ Accepted a request - Hitting the server API");
            return true;
        }

        // If the limit is exceeded, reject the request
        removeOldEntries(now); // Clean up old entries
        System.out.println("🚫 Dropped a request - Returning 429 Too Many Requests");
        return false;
    }
    
    private void removeOldEntries(long now) {
        counter.keySet().removeIf(windowKey ->
                (now - windowKey * windowSizeMillis) > windowSizeMillis // Remove if window is older than allowed timeframe
        );
    }

    public static void main(String[] args) {
        SlidingWindowCounterRateLimiter rateLimiter = new SlidingWindowCounterRateLimiter(5, 1000); // Limit: 5 requests, Window Size: 1 second
        Random random = new Random(); // Moved Random inside main
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
