package aRateLimiter.fixed_window_counter_03;

import java.util.Random;

public class FixedWindowCounterRateLimiter {
    private final int limit;              // Max requests allowed in a time window
    private final long windowSizeMillis;  // Window duration in ms
    private long currentWindowStart;      // Start time of the current window
    private int count;                    // Requests seen in current window

    public FixedWindowCounterRateLimiter(int limit, long windowSizeMillis) {
        this.limit = limit;
        this.windowSizeMillis = windowSizeMillis;
        this.currentWindowStart = alignToWindow(System.currentTimeMillis());
        this.count = 0;
    }

    public static void main(String[] args) {
        FixedWindowCounterRateLimiter rateLimiter = new FixedWindowCounterRateLimiter(5, 2000);
        Random random = new Random();

        System.out.println("🧪 Testing Fixed Window Counter Rate Limiter...\n");

        for (int i = 0; i < 25; i++) {
            boolean allowed = rateLimiter.allowRequest();

            if (allowed) {
                System.out.printf("Request %2d → ✅ Accepted request — counted in current window%n", i + 1);
            } else {
                System.out.printf("Request %2d → ❌ Rejected request — window limit exceeded (429 Too Many Requests)%n", i + 1);
            }

            try {
                Thread.sleep(50 + random.nextInt(451)); // 50–500ms
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("\n🛑 Fixed window rate limiter simulation complete.");
    }

    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis();
        long alignedWindow = alignToWindow(now);

        // lazily reset
        if (alignedWindow != currentWindowStart) {
            currentWindowStart = alignedWindow;
            count = 0;
            System.out.printf("⛽ New time window started — counter reset to 0 (window starting at %d)%n", currentWindowStart);
        }

        if (count < limit) {
            count++;
            return true;
        }

        return false;
    }

    // Your alignToWindow function’s job is to take any given timestamp
    // and "snap" it to the start time of its containing fixed window,
    // so the rate limiter knows which bucket of requests it belongs to.
    private long alignToWindow(long timeMillis) {
        return (timeMillis / windowSizeMillis) * windowSizeMillis;
    }
}
