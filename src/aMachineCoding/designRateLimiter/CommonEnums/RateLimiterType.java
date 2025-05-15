package aMachineCoding.designRateLimiter.CommonEnums;

/**
 * Enumeration of supported rate limiter types.
 */
public enum RateLimiterType {
    TOKEN_BUCKET,
    LEAKY_BUCKET,
    FIXED_WINDOW_COUNTER,
    SLIDING_WINDOW_LOG,
    SLIDING_WINDOW_COUNTER
}
