package aMachineCoding.designRateLimiter.Algorithms;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TokenBucketStrategy implements IRateLimiter {

    private final int bucketCapacity;                             // Maximum tokens per bucket.
    private volatile int refreshRate;                             // Tokens added per refill interval (per second).
    private final Bucket globalBucket;                            // Global token bucket used when no key is provided.
    private final ConcurrentHashMap<String, Bucket> userBuckets;  // Map of per‑user buckets indexed by a consistent rate limiting key.
    private final ScheduledExecutorService scheduler;             // Scheduled executor for automatic token refill.
    private final long refillIntervalMillis;                      // Refill interval in milliseconds (1000 ms = 1 sec).

    private class Bucket {
        private int tokens;
        private final ReentrantLock lock = new ReentrantLock();

        public Bucket(int initialTokens) {
            this.tokens = initialTokens;
        }

        public boolean tryConsume() {
            lock.lock();
            try {
                if (tokens > 0) {
                    tokens--;
                    return true;
                }
                return false;
            } finally {
                lock.unlock();
            }
        }

        public void refill() {
            lock.lock();
            try {
                tokens = Math.min(bucketCapacity, tokens + refreshRate);
            } finally {
                lock.unlock();
            }
        }
    }

    public TokenBucketStrategy(int bucketCapacity, int refreshRate) {
        this.bucketCapacity = bucketCapacity;
        this.refreshRate = refreshRate;
        this.globalBucket = new Bucket(bucketCapacity);
        this.userBuckets = new ConcurrentHashMap<>();
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        this.refillIntervalMillis = 1000;  // 1 second refill interval.
        startRefillTask();
    }

    private void startRefillTask() {
        scheduler.scheduleAtFixedRate(() -> {
            // Refill global bucket.
            globalBucket.refill();
            // Refill each per‑user bucket.
            for (Bucket bucket : userBuckets.values()) {
                bucket.refill();
            }
        }, refillIntervalMillis, refillIntervalMillis, TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean giveAccess(String rateLimitKey) {
        if (rateLimitKey != null && !rateLimitKey.isEmpty()) {
            // Use a per‑user bucket (always created with the same key for a given user).
            Bucket bucket = userBuckets.computeIfAbsent(rateLimitKey, key -> new Bucket(bucketCapacity));
            return bucket.tryConsume();
        } else {
            // Use the global bucket.
            return globalBucket.tryConsume();
        }
    }

    @Override
    public void updateConfiguration(Map<String, Object> config) {
        if (config.containsKey("refreshRate")) {
            this.refreshRate = (int) config.get("refreshRate");
        }
    }

    @Override
    public void shutdown() {
        scheduler.shutdownNow();
    }
}
