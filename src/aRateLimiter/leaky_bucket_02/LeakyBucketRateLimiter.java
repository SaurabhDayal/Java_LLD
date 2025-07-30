package aRateLimiter.leaky_bucket_02;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LeakyBucketRateLimiter {
    private final int capacity;                            // Max requests the bucket can hold
    private final long leakRate;                           // Leak interval in milliseconds
    private final Queue<Long> bucket = new LinkedList<>(); // Queue acting as the bucket
    private final ScheduledExecutorService ticker;         // Scheduler to simulate leak intervals

    public LeakyBucketRateLimiter(int capacity, long leakRate) {
        this.capacity = capacity;
        this.leakRate = leakRate;

        this.ticker = Executors.newSingleThreadScheduledExecutor();
        this.ticker.scheduleAtFixedRate(this::startLeaking,
                leakRate, leakRate, TimeUnit.MILLISECONDS);
    }

    public static void main(String[] args) {
        LeakyBucketRateLimiter lb = new LeakyBucketRateLimiter(5, 500); // 5-capacity bucket, leaks every 500ms
        Random random = new Random();

        for (int i = 1; i <= 25; i++) {
            try {
                Thread.sleep(50 + random.nextInt(451)); // Random delay between 50–500 ms
            } catch (InterruptedException ignored) {
            }

            boolean accepted = lb.allow();
            if (accepted) {
                System.out.printf("Request %2d → ✅ Accepted request — queued in bucket%n", i);
            } else {
                System.out.printf("Request %2d → ❌ Dropped request — bucket full (429 Too Many Requests)%n", i);
            }
        }

        try {
            Thread.sleep(3000); // Allow time for leak thread to process
        } catch (InterruptedException ignored) {
        }

        lb.stop();
    }

    private synchronized void startLeaking() {
        if (!bucket.isEmpty()) {
            bucket.poll(); // Leak one request from the bucket
            System.out.println("🚰 Leaked request — hitting downstream API");
        }
    }

    public synchronized boolean allow() {
        if (bucket.size() < capacity) {
            bucket.add(System.currentTimeMillis()); // Add timestamp to simulate tracking
            return true;
        }
        return false;
    }

    public void stop() {
        ticker.shutdown();
        System.out.println("\n🛑 Leak thread stopped.");
    }
}
