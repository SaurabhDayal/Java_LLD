package aRateLimiter.token_bucket_01;

import aRateLimiter.RateLimiter;

public class TokenBucketRateLimiter implements RateLimiter {
    private final TokenBucket tokenBucket;

    public TokenBucketRateLimiter(int capacity, int refillRate) {
        this.tokenBucket = new TokenBucket(capacity, refillRate);
    }

    @Override
    public boolean allowRequest() {
        return tokenBucket.allowRequest();
    }
}
