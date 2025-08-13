package aRateLimiter.sliding_window_counter_05;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowCounterRateLimiter {
    private final int limit;                                // Max requests allowed in the sliding window
    private final long windowSizeMillis;                    // Sliding window duration in milliseconds
    private final ConcurrentHashMap<Long, Integer> counter; // Map to track counts per window

    public SlidingWindowCounterRateLimiter(int limit, long windowSizeMillis) {
        this.limit = limit;
        this.windowSizeMillis = windowSizeMillis;
        this.counter = new ConcurrentHashMap<>();
    }

    public static void main(String[] args) {
        SlidingWindowCounterRateLimiter rateLimiter = new SlidingWindowCounterRateLimiter(5, 1000); // 5 requests/sec
        Random random = new Random();

        System.out.println("🧪 Testing Sliding Window Counter Rate Limiter...\n");

        for (int i = 0; i < 25; i++) {
            boolean allowed = rateLimiter.allowRequest();
            System.out.printf("Request %2d → %s\n", i + 1, allowed ? "✅ Allowed" : "❌ Rejected");

            try {
                Thread.sleep(50 + random.nextInt(201)); // 50–250 ms
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("\n🛑 Simulation complete.");
    }

    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis();
        long windowKey = now / windowSizeMillis;

        counter.putIfAbsent(windowKey, 0);
        counter.putIfAbsent(windowKey - 1, 0);

        int currentCount = counter.get(windowKey);
        int previousCount = counter.get(windowKey - 1);

        double elapsedInWindowRatio = (double) (now % windowSizeMillis) / windowSizeMillis;
        double weightedPrevious = previousCount * (1 - elapsedInWindowRatio);
        double totalCount = weightedPrevious + currentCount;

        if (totalCount < limit) {
            counter.put(windowKey, currentCount + 1);
            removeOldEntries(windowKey);
            System.out.println("✅ Accepted — hitting downstream API");
            return true;
        }

        removeOldEntries(windowKey);
        System.out.println("🚫 Rejected — rate limit exceeded (429 Too Many Requests)");
        return false;
    }

    // lazily cleanup
    private void removeOldEntries(long currentWindowKey) {
        for (Long key : counter.keySet()) {
            if (key < currentWindowKey - 1) {
                counter.remove(key);
            }
        }
    }
}
