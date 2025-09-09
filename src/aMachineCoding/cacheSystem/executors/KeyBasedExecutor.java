package aMachineCoding.cacheSystem.executors;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class KeyBasedExecutor {

    private final int numExecutors;
    private final ExecutorService[] executors;

    public KeyBasedExecutor(int numExecutors) {
        this.numExecutors = numExecutors;
        this.executors = new ExecutorService[numExecutors];
        for (int i = 0; i < numExecutors; i++) {
            executors[i] = Executors.newSingleThreadExecutor();
        }
    }

    /**
     * Dispatch a task for a given key so that all tasks for that key run on the same single-thread executor.
     */
    public <T> CompletableFuture<T> submitTask(Object key, Supplier<T> task) {
        int index = getExecutorIndexForKey(key);
        ExecutorService executor = executors[index];
        return CompletableFuture.supplyAsync(task, executor);
    }

    /**
     * Determines the executor index for a key using a basic mod hash.
     */
    public int getExecutorIndexForKey(Object key) {
        return Math.abs(key.hashCode() % numExecutors);
    }

    /**
     * Shuts down all executors.
     */
    public void shutdown() {
        for (ExecutorService executor : executors) {
            executor.shutdown();
        }
    }
}

/*
KeyBasedExecutor:
-----------------
    - Maintains an array of single-thread executors (e.g., 4 executors).
    - A task is routed to an executor based on (key.hashCode() % numExecutors).
    - Guarantees:
        * All operations on the same key are executed on the same single-threaded executor,
          ensuring strict sequential consistency for that key.
        * Operations on different keys can be dispatched to different executors,
          allowing true parallelism across independent keys.

Why per-key execution?
----------------------
    - In cache/database systems, writes and reads for the same key must be ordered.
      Example:
          update("A", "Apple") → update("A", "Avocado")
      Without per-key dispatch, threads could reorder these, leading to stale or
      incorrect reads.
    - A global lock would enforce order but block all concurrency.
    - KeyBasedExecutor achieves balance:
        * Per-key ordering (no race conditions, no stale reads).
        * Concurrency across unrelated keys (high throughput).

Why key-based instead of user-based?
------------------------------------
    - User-based dispatch gives higher concurrency (different users can execute in parallel),
      but can still interleave writes/reads on the same key if two users touch it at once.
    - Key-based dispatch ensures **consistency of data itself**, independent of which user
      performs the operation. This makes it safer for a cache where correctness of key-values
      matters more than per-user concurrency.

Analogy:
--------
    Think of it as N checkout counters in a supermarket:
    - All transactions for a specific product barcode (key) always go to the same counter,
      so their updates stay in order.
    - Transactions for different products (different keys) can be handled at different counters
      in parallel.
*/
