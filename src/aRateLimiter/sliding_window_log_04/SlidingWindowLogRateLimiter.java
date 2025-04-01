package aRateLimiter.sliding_window_log_04;

import aRateLimiter.RateLimiter;

import java.util.LinkedList;
import java.util.Queue;

public class SlidingWindowLogRateLimiter implements RateLimiter {
    private final int limit;
    private final long windowSizeMillis;
    private final Queue<Long> timestamps = new LinkedList<>();

    public SlidingWindowLogRateLimiter(int limit, long windowSizeMillis) {
        this.limit = limit;
        this.windowSizeMillis = windowSizeMillis;
    }

    @Override
    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis();
        while (!timestamps.isEmpty() && timestamps.peek() < now - windowSizeMillis) {
            timestamps.poll();
        }
        if (timestamps.size() < limit) {
            timestamps.add(now);
            return true;
        }
        return false;
    }
}
