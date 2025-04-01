package aRateLimiter.token_bucket_01;

import java.util.concurrent.TimeUnit;

class TokenBucket {
    private final int capacity;   // Maximum number of tokens the bucket can hold
    private int tokens;           // Current number of tokens available in the bucket
    private long lastRefillTime;  // Time when the bucket was last refilled (in nanoseconds)
    private final int refillRate; // Number of tokens added per second

    // Constructor to initialize the TokenBucket with a given capacity and refill rate
    public TokenBucket(int capacity, int refillRate) {
        this.capacity = capacity;                  // Set the bucket's capacity
        this.tokens = capacity;                    // Initially, fill the bucket to its capacity
        this.refillRate = refillRate;              // Set the rate at which tokens are added
        this.lastRefillTime = System.nanoTime();   // Initialize last refill time to the current time
    }

    // Method to refill the bucket with tokens based on the elapsed time
    private void refill() {
        long now = System.nanoTime();  // Get the current time in nanoseconds
        long elapsedSeconds = TimeUnit.NANOSECONDS.toSeconds(now - lastRefillTime);  // Calculate how much time has passed since last refill
        if (elapsedSeconds > 0) {  // If some time has passed since the last refill
            // Add tokens according to the elapsed time and refill rate, but don't exceed the capacity
            tokens = Math.min(capacity, tokens + (int) (elapsedSeconds * refillRate));
            lastRefillTime = now;  // Update the last refill time to the current time
        }
    }

    // Method to check if a request can be allowed based on available tokens
    public synchronized boolean allowRequest() {
        refill();  // Refill the tokens first before checking availability
        if (tokens > 0) {  // If there are tokens available in the bucket
            tokens--;  // Decrease the token count by 1 since a request is allowed
            return true;  // Allow the request
        }
        return false;  // If no tokens are available, deny the request
    }
}
