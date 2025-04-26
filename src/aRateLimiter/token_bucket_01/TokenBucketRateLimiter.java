package aRateLimiter.token_bucket_01;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TokenBucketRateLimiter {
    private final int capacity;                       // Maximum number of tokens the bucket can hold
    private int tokens;                               // Current available tokens
    private final int refillRate;                     // Number of tokens to add every second
    private final ScheduledExecutorService scheduler; // Scheduler to refill tokens periodically

    // Constructor initializes the token bucket with given capacity and refill rate
    public TokenBucketRateLimiter(int capacity, int refillRate) {
        this.capacity = capacity;
        this.tokens = capacity; // Initially, the bucket is full
        this.refillRate = refillRate;

        // Start the scheduler to refill tokens every second
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        this.scheduler.scheduleAtFixedRate(this::refill,
                1, 1, TimeUnit.SECONDS);
    }

    // Method to refill tokens up to the bucket's maximum capacity
    private synchronized void refill() {
        tokens = Math.min(capacity, tokens + refillRate);
        System.out.println("🔄 Refilled tokens: Current token count = " + tokens);
    }

    // Method to allow or reject a request based on available tokens
    public synchronized boolean allowRequest() {
        if (tokens > 0) {
            tokens--; // Consume one token for the request
            System.out.println("✅ Accepted a request - Hitting the server API");
            return true;
        }

        System.out.println("🚫 Dropped a request - Returning 429 Too Many Requests"); // No tokens available
        return false;
    }

    // Stops the token refilling scheduler
    public void stop() {
        scheduler.shutdown();
        System.out.println("🛑 Refill thread stopped.");
    }

    // Main method to test the Token Bucket Rate Limiter
    public static void main(String[] args) {
        TokenBucketRateLimiter rateLimiter = new TokenBucketRateLimiter(10, 5); // Capacity: 10 tokens, Refill Rate: 5 tokens/sec
        Random random = new Random();
        System.out.println("Testing Token Bucket Rate Limiter...\n");

        // Simulate 25 random-paced requests
        for (int i = 0; i < 25; i++) {
            System.out.println("Request " + (i + 1) + " allowed? " + rateLimiter.allowRequest());

            try {
                int sleepTime = 50 + random.nextInt(201); // Sleep between 50ms to 250ms randomly
                Thread.sleep(sleepTime);
            } catch (InterruptedException ignored) {
            }
        }

        rateLimiter.stop(); // Stop refilling after test
    }
}
