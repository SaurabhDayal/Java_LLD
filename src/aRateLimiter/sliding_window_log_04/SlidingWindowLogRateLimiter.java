package aRateLimiter.sliding_window_log_04;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class SlidingWindowLogRateLimiter {
    private final int limit;                     // Max requests allowed in the sliding window
    private final long windowSizeMillis;         // Sliding window size in milliseconds
    private final Queue<Long> timestamps;        // Queue to hold timestamps of recent requests

    public SlidingWindowLogRateLimiter(int limit, long windowSizeMillis) {
        this.limit = limit;
        this.windowSizeMillis = windowSizeMillis;
        this.timestamps = new LinkedList<>();
    }

    public static void main(String[] args) {
        SlidingWindowLogRateLimiter rateLimiter = new SlidingWindowLogRateLimiter(5, 1000); // 5 requests per 1 second
        Random random = new Random();

        for (int i = 0; i < 25; i++) {
            boolean allowed = rateLimiter.allowRequest();
            System.out.printf("Request %2d → %s\n", i + 1, allowed ? "✅ Allowed" : "❌ Rejected");

            try {
                Thread.sleep(50 + random.nextInt(201)); // 50–250 ms
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Properly restore the interrupted status
            }
        }

        System.out.println("\n🛑 Simulation complete.");
    }

    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis();

        // lazily Remove timestamps older than the start of the current window
        while (!timestamps.isEmpty() && timestamps.peek() < now - windowSizeMillis) {
            timestamps.poll();
        }

        if (timestamps.size() < limit) {
            timestamps.add(now);
            System.out.println("✅ Request accepted — hitting downstream API");
            return true;
        }

        System.out.println("🚫 Request rejected — rate limit exceeded (429 Too Many Requests)");
        return false;
    }
}
