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
    // ON DEMAND REFILL IN TOKEN BUCKET
    // HOWEVER IN LEAKY BUCKET WE USED FIXED TIMER TO LEAK REQUESTS
    private void refill() {
        long now = System.nanoTime();  // Get the current time in nanoseconds

        // Calculate how much time has passed since the last refill
        long elapsedSeconds = TimeUnit.NANOSECONDS.toSeconds(now - lastRefillTime);

        if (elapsedSeconds > 0) {  // If at least 1 second has passed since the last refill
            // The bucket refills dynamically based on how much time has passed
            // The new token count is calculated as:
            //    tokens = min(capacity, tokens + (elapsedSeconds * refillRate))
            // This ensures we don't exceed the bucket's max capacity
            tokens = Math.min(capacity, tokens + (int) (elapsedSeconds * refillRate));

            // Update lastRefillTime to the current time, ensuring future requests get accurate refill calculations
            lastRefillTime = now;

            // Log the refill action
            System.out.println("Refilled tokens: Current token count = " + tokens);
        }
    }

    // Method to check if a request can be allowed based on available tokens
    public synchronized boolean allowRequest() {
        refill();  // Refill the tokens before checking availability

        if (tokens > 0) {  // If there are tokens available in the bucket
            tokens--;  // Consume a token since a request is being processed

            // ✅ API request is sent, meaning the request is successfully processed
            System.out.println("Accepted a request - Hitting the server API");
            return true;  // Allow the request
        }

        // 🚫 No tokens available, request is denied, returning HTTP 429 Too Many Requests
        System.out.println("Dropped a request - Returning 429 Too Many Requests");
        return false;  // Deny the request due to rate limit
    }
}
