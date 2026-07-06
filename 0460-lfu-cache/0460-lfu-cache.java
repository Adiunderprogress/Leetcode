import java.util.HashMap;
import java.util.Map;

class LFUCache {
    
    // Node structure to store cache elements
    private class Node {
        int key;
        int value;
        int freq;
        Node prev;
        Node next;
        
        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.freq = 1; // Default frequency for new elements is 1
        }
    }
    
    // Custom Doubly Linked List to maintain LRU order within the same frequency group
    private class DoublyLinkedList {
        Node head;
        Node tail;
        int size;
        
        DoublyLinkedList() {
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }
        
        void addToHead(Node node) {
            node.next = head.next;
            node.next.prev = node;
            head.next = node;
            node.prev = head;
            size++;
        }
        
        void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }
        
        Node removeTail() {
            if (size == 0) return null;
            Node lruNode = tail.prev;
            removeNode(lruNode);
            return lruNode;
        }
    }

    private final int capacity;
    private int minFreq;
    private final Map<Integer, Node> cache;
    private final Map<Integer, DoublyLinkedList> freqMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        this.cache = new HashMap<>();
        this.freqMap = new HashMap<>();
    }
    
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        Node node = cache.get(key);
        updateFrequency(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if (capacity == 0) return;
        
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value; // Update value
            updateFrequency(node);
        } else {
            // Evict if cache is at maximum capacity
            if (cache.size() >= capacity) {
                DoublyLinkedList minFreqList = freqMap.get(minFreq);
                Node evictedNode = minFreqList.removeTail();
                if (evictedNode != null) {
                    cache.remove(evictedNode.key);
                }
            }
            
            // Insert the new node
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            minFreq = 1; // Reset minFreq to 1 for the new element
            
            DoublyLinkedList list = freqMap.computeIfAbsent(1, k -> new DoublyLinkedList());
            list.addToHead(newNode);
        }
    }
    
    // Helper method to upgrade a node's frequency list location
    private void updateFrequency(Node node) {
        int currentFreq = node.freq;
        DoublyLinkedList oldList = freqMap.get(currentFreq);
        oldList.removeNode(node);
        
        // If the old minimum frequency list becomes empty, increment global minFreq tracker
        if (currentFreq == minFreq && oldList.size == 0) {
            minFreq++;
        }
        
        node.freq++;
        DoublyLinkedList newList = freqMap.computeIfAbsent(node.freq, k -> new DoublyLinkedList());
        newList.addToHead(node);
    }
}