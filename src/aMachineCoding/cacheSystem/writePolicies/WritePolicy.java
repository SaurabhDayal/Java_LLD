package WritePolicies;

import aMachineCoding.cacheSystem.storageMechanisms.CacheStorage;
import aMachineCoding.cacheSystem.storageMechanisms.DBStorage;

public interface WritePolicy<K, V> {
    /**
     * Write a key/value pair to both cache storage and DB storage concurrently.
     * This is the write‑through policy.
     */
    void write(K key, V value, CacheStorage<K, V> cacheStorage, DBStorage<K, V> dbStorage) throws Exception;
}
