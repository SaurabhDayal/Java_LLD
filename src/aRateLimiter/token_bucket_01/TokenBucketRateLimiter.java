package aRateLimiter.token_bucket_01;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TokenBucketRateLimiter {
    private final int capacity;
    private int tokens;
    private final int refillRate;
    private final ScheduledExecutorService scheduler;

    public TokenBucketRateLimiter(int capacity, int refillRate) {
        this.capacity = capacity;
        this.tokens = capacity;
        this.refillRate = refillRate;

        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        this.scheduler.scheduleAtFixedRate(this::refill, 1, 1, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        TokenBucketRateLimiter rateLimiter = new TokenBucketRateLimiter(5, 2);
        Random random = new Random();

        for (int i = 1; i <= 25; i++) {
            boolean allowed = rateLimiter.allowRequest(i);

            try {
                int sleepTime = 50 + random.nextInt(451); // 50ms–500ms
                Thread.sleep(sleepTime);
            } catch (InterruptedException ignored) {
            }
        }

        rateLimiter.stop();
        System.out.println("\n🛑 Refill thread stopped.");
    }

    private synchronized void refill() {
        tokens = Math.min(capacity, tokens + refillRate);
        System.out.printf("⛽ Refilled %d tokens — current bucket: %d%n", refillRate, tokens);
    }

    public synchronized boolean allowRequest(int requestId) {
        if (tokens > 0) {
            tokens--;
            System.out.printf("Request %2d → ✅ Accepted — token consumed%n", requestId);
            return true;
        }

        System.out.printf("Request %2d → ❌ Rejected — no tokens available (429 Too Many Requests)%n", requestId);
        return false;
    }

    public void stop() {
        scheduler.shutdown();
    }
}
