package aMachineCoding.rateLimiter;

import aMachineCoding.rateLimiter.CommonEnums.RateLimiterType;
import aMachineCoding.rateLimiter.Controller.RateLimiterController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        demonstrateRateLimiting();
    }

    private static void demonstrateRateLimiting() {
        // Configure the token bucket: maximum 5 tokens and a refill rate of 1 token per second.
        Map<String, Object> config = new HashMap<>();
        config.put("capacity", 5);
        config.put("refreshRate", 1);

        ExecutorService executor = Executors.newFixedThreadPool(10);
        RateLimiterController controller = new RateLimiterController(RateLimiterType.TOKEN_BUCKET, config, executor);

        // --- Example 1: Global rate limiting – Burst of requests ---
        System.out.println("=== EXAMPLE 1: Global rate limiting – Burst of requests ===");
        // For global requests, we pass a null key so that all requests use the global bucket.
        sendBurstRequests(controller, 10, null);

        // --- Example 2: Global rate limiting – After waiting for tokens refill ---
        System.out.println("\n=== EXAMPLE 2: Global rate limiting – After waiting for tokens refill ===");
        System.out.println("Waiting 5 seconds for tokens to refill...");
        sleep(5000);
        sendBurstRequests(controller, 10, null);

        // --- Example 3: Per-user rate limiting ---
        // Here we pass a consistent user key (e.g., "user1") so that all requests share the same bucket.
        String[] users = {"user1", "user2", "user3"};
        for (String user : users) {
            System.out.println("\nRequests for " + user + ":");
            sendBurstRequests(controller, 7, user);
        }

        // --- Example 4: High concurrency scenario ---
        System.out.println("\n=== EXAMPLE 4: High concurrency scenario ===");
        sleep(5000);
        // Submit 20 requests rapidly using global rate limiting (null key) for demonstration.
        List<CompletableFuture<Boolean>> futures = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            futures.add(controller.processRequest(null));
        }

        // --- CompletableFuture.allOf explanation ---
        // 'futures.toArray(new CompletableFuture[0])' converts the list of futures to an array.
        // 'CompletableFuture.allOf(...).join()' waits (blocks) until all the supplied futures complete.
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        // After all futures are complete, we calculate how many requests were allowed.
        // The stream filters the completed futures (each future.join() returns the boolean result).
        long allowed = futures.stream().filter(CompletableFuture::join).count();
        System.out.printf("High concurrency results: %d allowed, %d blocked%n", allowed, 20 - allowed);

        controller.shutdown();
    }

    private static void sendBurstRequests(RateLimiterController controller, int count, String rateLimitKey) {

        List<CompletableFuture<Boolean>> futures = new ArrayList<>();

        for (int i = 1; i <= count; i++) {
            // In this example,
            // the same rateLimitKey is used for each request so that they share the same bucket.
            futures.add(controller.processRequest(rateLimitKey));
        }

        // Wait for all requests in the burst to complete.
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();  // Blocks until all futures complete.

        // Count how many requests were allowed.
        long allowed = futures.stream().filter(CompletableFuture::join).count(); // Joins each future to get its boolean result.

        System.out.printf("Results: %d allowed, %d blocked (total: %d)%n", allowed, count - allowed, count);
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
