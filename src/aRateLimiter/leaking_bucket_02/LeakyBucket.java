package aRateLimiter.leaking_bucket_02;

import java.util.LinkedList;
import java.util.Queue;

class LeakyBucket {
    private final int capacity;
    private final long leakRateMillis;
    private final Queue<Long> queue = new LinkedList<>();

    public LeakyBucket(int capacity, long leakRateMillis) {
        this.capacity = capacity;
        this.leakRateMillis = leakRateMillis;
    }

    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis();
        if (queue.size() < capacity) {
            queue.add(now);
            return true;
        }

        Long earliest = queue.peek();
        if (earliest != null && now - earliest >= leakRateMillis) {
            queue.poll();
            queue.add(now);
            return true;
        }
        return false;
    }
}

