package aRateLimiter.leaky_bucket_02;

import aRateLimiter.RateLimiter;

public class LeakyBucketRateLimiter implements RateLimiter {
    private final LeakyBucket bucket;

    public LeakyBucketRateLimiter(int capacity, long leakRateMillis) {
        this.bucket = new LeakyBucket(capacity, leakRateMillis);
    }

    @Override
    public boolean allowRequest() {
        return bucket.allowRequest();
    }
}
