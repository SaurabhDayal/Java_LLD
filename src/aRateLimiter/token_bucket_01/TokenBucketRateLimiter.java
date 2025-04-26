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
        startRefilling();
    }

    private void startRefilling() {
        scheduler.scheduleAtFixedRate(() -> {
            synchronized (this) {
                tokens = Math.min(capacity, tokens + refillRate);
                System.out.println("🔄 Refilled tokens: Current token count = " + tokens);
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

    public synchronized boolean allowRequest() {
        if (tokens > 0) {
            tokens--;
            System.out.println("✅ Accepted a request - Hitting the server API");
            return true;
        }

        System.out.println("🚫 Dropped a request - Returning 429 Too Many Requests");
        return false;
    }

    public void stop() {
        scheduler.shutdown();
        System.out.println("🛑 Refill thread stopped.");
    }

    public static void main(String[] args) {
        TokenBucketRateLimiter rateLimiter = new TokenBucketRateLimiter(10, 5);
        Random random = new Random();

        System.out.println("Testing Token Bucket Rate Limiter...\n");

        for (int i = 0; i < 25; i++) {
            System.out.println("Request " + (i + 1) + " allowed? " + rateLimiter.allowRequest());

            try {
                int sleepTime = 50 + random.nextInt(201); // 50ms to 250ms
                Thread.sleep(sleepTime);
            } catch (InterruptedException ignored) {
            }
        }

        rateLimiter.stop();
    }
}
