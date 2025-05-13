package aRateLimiter.leaky_bucket_02;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LeakyBucketRateLimiter {
    private final int capacity;                            // Maximum number of requests the bucket can hold
    private final long leakRateMillis;                     // Time interval (in milliseconds) for leaking requests
    private final Queue<Long> bucket = new LinkedList<>(); // Bucket to store incoming request timestamps
    private final ScheduledExecutorService scheduler;      // Scheduler to periodically leak requests

    // Constructor initializes the leaky bucket with given capacity and leak rate
    public LeakyBucketRateLimiter(int capacity, long leakRateMillis) {
        this.capacity = capacity;
        this.leakRateMillis = leakRateMillis;

        // Start the leak scheduler to leak requests at fixed intervals
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        this.scheduler.scheduleAtFixedRate(this::leakRequest,
                leakRateMillis, leakRateMillis, TimeUnit.MILLISECONDS);
    }

    // Method to leak (process) a request from the bucket
    private synchronized void leakRequest() {
        if (!bucket.isEmpty()) {
            bucket.poll(); // Remove the oldest request from the bucket
            System.out.println("✅ Leaked a request - Hitting the server API");
        }
    }

    // Method to check if a new request can be accepted into the bucket
    public synchronized boolean allowRequest() {
        if (bucket.size() < capacity) {
            bucket.add(System.currentTimeMillis()); // Add the request timestamp into the bucket
            System.out.println("✅ Accepted a request - Waiting to be processed");
            return true;
        }
        System.out.println("🚫 Dropped a request - Returning 429 Too Many Requests");
        return false;
    }

    // Stops the leak scheduler when no longer needed
    public void stop() {
        scheduler.shutdown();
        System.out.println("\n🛑 Leak thread stopped.");
    }

    // aMachineCoding.designFileSystem.Main method to test the Leaky Bucket Rate Limiter
    public static void main(String[] args) {
        LeakyBucketRateLimiter rateLimiter = new LeakyBucketRateLimiter(5, 1000); // Capacity: 5 requests, Leak Rate: 1 second
        Random random = new Random(); // Moved Random inside main
        System.out.println("Testing Leaky Bucket Rate Limiter with ScheduledExecutorService...\n");

        // Simulating 25 requests
        for (int i = 0; i < 25; i++) {
            System.out.println("Request " + (i + 1) + " allowed? " + rateLimiter.allowRequest());
            try {
                // Sleep for random time between 50ms and 250ms
                int sleepTimeMillis = 50 + random.nextInt(201);
                Thread.sleep(sleepTimeMillis);
            } catch (InterruptedException ignored) {
            }
        }

        rateLimiter.stop(); // Stop the leak scheduler once testing is done
    }
}
