package aRateLimiter.fixed_window_counter_03;

import aRateLimiter.RateLimiter;

import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowRateLimiter implements RateLimiter {
    private final int limit;
    private final long windowSizeMillis;
    private long windowStart;
    private final AtomicInteger count;

    public FixedWindowRateLimiter(int limit, long windowSizeMillis) {
        this.limit = limit;
        this.windowSizeMillis = windowSizeMillis;
        this.windowStart = System.currentTimeMillis();
        this.count = new AtomicInteger(0);
    }

    @Override
    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis();
        if (now - windowStart >= windowSizeMillis) {
            windowStart = now;
            count.set(0);
        }
        if (count.incrementAndGet() <= limit) {
            return true;
        }
        return false;
    }
}
