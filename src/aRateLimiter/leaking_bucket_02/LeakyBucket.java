package aRateLimiter.leaking_bucket_02;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

class LeakyBucket {
    private final int capacity;                            // Maximum number of requests the bucket can hold
    private final long leakRateMillis;                    // Time interval (in milliseconds) for leaking requests
    private final Queue<Long> queue = new LinkedList<>(); // Queue to store request timestamps
    private final Timer leakTimer;                        // Timer to schedule leaking requests at fixed intervals

    // Constructor initializes the bucket with a fixed capacity and leak rate
    public LeakyBucket(int capacity, long leakRateMillis) {
        this.capacity = capacity;
        this.leakRateMillis = leakRateMillis;

        // Start a timer to leak requests at fixed intervals
        this.leakTimer = new Timer(true); // Daemon thread (runs in the background)
        this.leakTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                leakRequest(); // Leak (process) a request at every interval
            }
        }, leakRateMillis, leakRateMillis);
    }

    // Leaks (removes) a request from the bucket at fixed intervals (simulating request processing)
    private synchronized void leakRequest() {
        if (!queue.isEmpty()) {
            queue.poll(); // Remove the oldest request from the queue (simulating sending request to server)
            System.out.println("Leaked a request - Hitting the server API"); // ✅ API request is sent here
        }
    }

    // Method to check if a request can be added to the bucket
    public synchronized boolean allowRequest() {
        if (queue.size() < capacity) {
            // Bucket is not full, accept the request
            queue.add(System.currentTimeMillis()); // Add request timestamp to the queue
            System.out.println("Accepted a request - Waiting to be processed"); // ✅ Request is added but not sent yet
            return true;
        }

        // Bucket is full, reject the request
        System.out.println("Dropped a request - Returning 429 Too Many Requests"); // 🚫 API is NOT hit, request is rejected
        return false;
    }

    // Stops the leak timer when no longer needed
    public void stop() {
        leakTimer.cancel();
    }
}
