package aMachineCoding.cacheSystem.mainCache;

import aMachineCoding.cacheSystem.evictionAlgorithms.EvictionAlgorithm;
import aMachineCoding.cacheSystem.executors.KeyBasedExecutor;
import aMachineCoding.cacheSystem.storageMechanisms.CacheStorage;
import aMachineCoding.cacheSystem.storageMechanisms.DBStorage;
import aMachineCoding.cacheSystem.writePolicies.WritePolicy;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class Cache<K, V> {

    private final CacheStorage<K, V> cacheStorage;
    private final DBStorage<K, V> dbStorage;
    private final WritePolicy<K, V> writePolicy;
    private final EvictionAlgorithm<K> evictionAlgorithm;
    private final KeyBasedExecutor keyBasedExecutor;

    public Cache(CacheStorage<K, V> cacheStorage,
                 DBStorage<K, V> dbStorage,
                 WritePolicy<K, V> writePolicy,
                 EvictionAlgorithm<K> evictionAlgorithm,
                 int numExecutors) {
        this.cacheStorage = cacheStorage;
        this.dbStorage = dbStorage;
        this.writePolicy = writePolicy;
        this.evictionAlgorithm = evictionAlgorithm;
        this.keyBasedExecutor = new KeyBasedExecutor(numExecutors);
    }

    /**
     * Reads data from the cache for the given key.
     * Updates the eviction algorithm as the key is accessed.
     */
    public CompletableFuture<V> accessData(K key) {
        return keyBasedExecutor.submitTask(key, () -> {
            try {
                if (!cacheStorage.containsKey(key)) {
                    throw new Exception("Key not found in cache: " + key);
                }
                evictionAlgorithm.keyAccessed(key);
                return cacheStorage.get(key);
            } catch (Exception e) {
                throw new CompletionException(e);
            }
        });
    }

    /**
     * Writes (or updates) data in both the cache and DB storage using the write-through policy.
     * If the key is new and the cache is at capacity, evicts the least recently used key from the cache.
     */
    public CompletableFuture<Void> updateData(K key, V value) {
        return keyBasedExecutor.submitTask(key, () -> {
            try {
                if (cacheStorage.containsKey(key)) {
                    // Update case: perform concurrent write.
                    writePolicy.write(key, value, cacheStorage, dbStorage);
                    evictionAlgorithm.keyAccessed(key);
                } else {
                    // New key: If the cache is full, evict one key.
                    if (cacheStorage.size() >= cacheStorage.getCapacity()) {
                        K evictedKey = evictionAlgorithm.evictKey();   // eviction algorithm decides which key to evict
                        if (evictedKey != null) {
                            // Figure out which executor handles the current key being inserted/updated
                            int currentIndex = keyBasedExecutor.getExecutorIndexForKey(key);
                            // Figure out which executor handles the evicted key
                            int evictedIndex = keyBasedExecutor.getExecutorIndexForKey(evictedKey);

                            if (currentIndex == evictedIndex) {
                                // ✅ Case 1: Same executor handles both keys
                                // Safe to remove the evicted key directly, since ordering is preserved
                                cacheStorage.remove(evictedKey);
                            } else {
                                // ✅ Case 2: Different executors handle currentKey and evictedKey
                                // We must schedule eviction on the evictedKey’s own executor
                                CompletableFuture<Void> removalFuture = keyBasedExecutor.submitTask(evictedKey, () -> {
                                    try {
                                        cacheStorage.remove(evictedKey);
                                        return null;
                                    } catch (Exception ex) {
                                        throw new CompletionException(ex);
                                    }
                                });
                                removalFuture.join(); // Wait for eviction to finish before proceeding
                            }
                        }
                    }
                    // Write the new key/value concurrently to both storages.
                    writePolicy.write(key, value, cacheStorage, dbStorage);
                    evictionAlgorithm.keyAccessed(key);
                }
                return null;
            } catch (Exception e) {
                throw new CompletionException(e);
            }
        });
    }

    /**
     * Shuts down all executors.
     */
    public void shutdown() {
        keyBasedExecutor.shutdown();
    }
}
