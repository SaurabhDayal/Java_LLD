package aRateLimiter;

import aRateLimiter.fixed_window_counter_03.FixedWindowRateLimiter;
import aRateLimiter.leaking_bucket_02.LeakyBucketRateLimiter;
import aRateLimiter.sliding_window_counter_05.SlidingWindowCounterRateLimiter;
import aRateLimiter.sliding_window_log_04.SlidingWindowLogRateLimiter;
import aRateLimiter.token_bucket_01.TokenBucketRateLimiter;

import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Map for selecting strategies dynamically
        Map<Integer, Supplier<RateLimiter>> rateLimiterStrategies = Map.of(
                1, () -> new TokenBucketRateLimiter(10, 5),
                2, () -> new LeakyBucketRateLimiter(5, 1000),
                3, () -> new FixedWindowRateLimiter(5, 1000),
                4, () -> new SlidingWindowLogRateLimiter(5, 1000),
                5, () -> new SlidingWindowCounterRateLimiter(5, 1000)
        );

        // Display menu
        System.out.println("Select Rate Limiting Strategy:");
        System.out.println("1: Token Bucket");
        System.out.println("2: Leaky Bucket");
        System.out.println("3: Fixed Window Counter");
        System.out.println("4: Sliding Window Log");
        System.out.println("5: Sliding Window Counter");
        System.out.print("Enter choice (1-5): ");

        // Validate user input
        int choice = -1;
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
        }
        scanner.nextLine(); // Consume newline character

        // Get the selected rate limiter
        Supplier<RateLimiter> rateLimiterSupplier = rateLimiterStrategies.get(choice);
        if (rateLimiterSupplier == null) {
            System.out.println("Invalid choice! Exiting...");
            System.exit(1);
        }

        RateLimiter rateLimiter = rateLimiterSupplier.get();
        System.out.println("\nTesting selected strategy...\n");

        // Simulate 15 requests with a 500ms delay
        for (int i = 0; i < 15; i++) {
            System.out.println("Request " + (i + 1) + " allowed? " + rateLimiter.allowRequest());
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
        }
        scanner.close();
    }
}
