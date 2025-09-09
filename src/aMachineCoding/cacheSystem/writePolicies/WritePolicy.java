package aMachineCoding.cacheSystem.writePolicies;

import aMachineCoding.cacheSystem.storageMechanisms.CacheStorage;
import aMachineCoding.cacheSystem.storageMechanisms.DBStorage;

public interface WritePolicy<K, V> {
    void write(K key, V value, CacheStorage<K, V> cacheStorage, DBStorage<K, V> dbStorage) throws Exception;
}
