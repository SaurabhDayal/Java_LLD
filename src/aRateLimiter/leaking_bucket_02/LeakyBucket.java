package aRateLimiter.leaking_bucket_02;

import java.util.LinkedList;
import java.util.Queue;

class LeakyBucket {
    private final int capacity;                            // Maximum number of requests the bucket can hold
    private final long leakRateMillis;                     // Time interval (in milliseconds) for leaking requests
    private final Queue<Long> queue = new LinkedList<>();  // Queue to store request timestamps

    // Constructor initializes the bucket with a fixed capacity and leak rate
    public LeakyBucket(int capacity, long leakRateMillis) {
        this.capacity = capacity;              // Set the maximum allowed requests in the bucket
        this.leakRateMillis = leakRateMillis;  // Define how fast requests leak (processed)
    }

    // Method to check if a request can be allowed based on the leaky bucket algorithm
    public synchronized boolean allowRequest() {
        long now = System.currentTimeMillis();  // Get the current timestamp in milliseconds

        // If the bucket is not full, add the request and allow it
        if (queue.size() < capacity) {
            queue.add(now);  // Add the request timestamp to the queue
            return true;     // Request is allowed
        }

        // Check the earliest request in the queue (oldest request)
        Long earliest = queue.peek();

        // If the oldest request is older than the leak rate, remove it (leak out) and allow the new request
        if (earliest != null && now - earliest >= leakRateMillis) {
            queue.poll();    // Remove the oldest request to create space
            queue.add(now);  // Add the new request timestamp
            return true;     // Request is allowed
        }

        // If the bucket is full and no request has leaked, deny the request
        return false;
    }
}
