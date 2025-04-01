package aRateLimiter.sliding_window_counter_05;

import aRateLimiter.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowCounterRateLimiter implements RateLimiter {
    private final int limit;
    private final long windowSizeMillis;
    private final ConcurrentHashMap<Long, Integer> counter = new ConcurrentHashMap<>();

    public SlidingWindowCounterRateLimiter(int limit, long windowSizeMillis) {
        this.limit = limit;
        this.windowSizeMillis = windowSizeMillis;
    }

    @Override
    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis();
        long windowKey = now / (windowSizeMillis / 2);

        counter.putIfAbsent(windowKey, 0);
        counter.putIfAbsent(windowKey - 1, 0);

        int currentCount = counter.get(windowKey);
        int previousCount = counter.get(windowKey - 1);

        double weightedCount = previousCount * 0.5 + currentCount;
        if (weightedCount < limit) {
            counter.put(windowKey, currentCount + 1);
            return true;
        }
        return false;
    }
}
