package aMachineCoding.cacheSystem.evictionAlgorithms;

import aMachineCoding.cacheSystem.utilityClasses.DoublyLinkedList;
import aMachineCoding.cacheSystem.utilityClasses.DoublyLinkedListNode;

import java.util.HashMap;
import java.util.Map;

public class LRUEvictionAlgorithm<K> implements EvictionAlgorithm<K> {

    private final DoublyLinkedList<K> dll;
    private final Map<K, DoublyLinkedListNode<K>> keyToNodeMap;

    public LRUEvictionAlgorithm() {
        this.dll = new DoublyLinkedList<>();
        this.keyToNodeMap = new HashMap<>();
    }

    @Override
    public synchronized void keyAccessed(K key) throws Exception {
        if (keyToNodeMap.containsKey(key)) {
            // Move the node to the tail (most recently used).
            DoublyLinkedListNode<K> node = keyToNodeMap.get(key);
            dll.detachNode(node);
            dll.addNodeAtTail(node);
        } else {
            // New key: add it to the tail.
            DoublyLinkedListNode<K> newNode = new DoublyLinkedListNode<>(key);
            dll.addNodeAtTail(newNode);
            keyToNodeMap.put(key, newNode);
        }
    }

    @Override
    public synchronized K evictKey() throws Exception {
        // Evict the least recently used key (from the head).
        DoublyLinkedListNode<K> nodeToEvict = dll.getHead();
        if (nodeToEvict == null) {
            return null;
        }
        K evictKey = nodeToEvict.getValue();
        dll.removeHead();
        keyToNodeMap.remove(evictKey);
        return evictKey;
    }
}

