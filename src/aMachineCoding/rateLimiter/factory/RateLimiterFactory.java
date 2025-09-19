package aMachineCoding.rateLimiter.factory;

import aMachineCoding.rateLimiter.Algorithms.IRateLimiter;
import aMachineCoding.rateLimiter.Algorithms.TokenBucketStrategy;
import aMachineCoding.rateLimiter.CommonEnums.RateLimiterType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class RateLimiterFactory {

    private static final Map<RateLimiterType, Function<Map<String, Object>, IRateLimiter>> limiterFactories = new HashMap<>();

    static {
        // Factory registration for the Token Bucket rate limiter.
        limiterFactories.put(RateLimiterType.TOKEN_BUCKET, config -> {
            int capacity = (int) config.getOrDefault("capacity", 10);
            int refreshRate;
            if (config.containsKey("refreshRate")) {
                refreshRate = (int) config.get("refreshRate");
            } else {
                double tokensPerSecond = (double) config.getOrDefault("tokensPerSecond", 10.0);
                refreshRate = (int) Math.round(tokensPerSecond);
            }
            return new TokenBucketStrategy(capacity, refreshRate);
        });

        // Additional strategies (FIXED_WINDOW, SLIDING_WINDOW, LEAKY_BUCKET, etc.) can be registered here.
    }

    public static IRateLimiter createLimiter(RateLimiterType type, Map<String, Object> config) {
        Function<Map<String, Object>, IRateLimiter> factory = limiterFactories.get(type);
        if (factory == null) {
            throw new IllegalArgumentException("Unsupported rate limiter type: " + type);
        }
        return factory.apply(config);
    }

    public static void registerLimiterFactory(RateLimiterType type, Function<Map<String, Object>, IRateLimiter> factory) {
        limiterFactories.put(type, factory);
    }
}
