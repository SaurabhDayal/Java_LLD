package aRateLimiter.token_bucket_01;

import java.util.concurrent.TimeUnit;

class TokenBucket {
    private final int capacity;
    private int tokens;
    private long lastRefillTime;
    private final int refillRate;

    public TokenBucket(int capacity, int refillRate) {
        this.capacity = capacity;
        this.tokens = capacity;
        this.refillRate = refillRate;
        this.lastRefillTime = System.nanoTime();
    }

    private void refill() {
        long now = System.nanoTime();
        long elapsedSeconds = TimeUnit.NANOSECONDS.toSeconds(now - lastRefillTime);
        if (elapsedSeconds > 0) {
            tokens = Math.min(capacity, tokens + (int) (elapsedSeconds * refillRate));
            lastRefillTime = now;
        }
    }

    public synchronized boolean allowRequest() {
        refill();
        if (tokens > 0) {
            tokens--;
            return true;
        }
        return false;
    }
}

