package daria.itmo;

/**
 * Created by Daria on 10.09.2016.
 */
public class LRUCache<K, V> extends AbstractLRUCache<K, V> {

    public LRUCache(int size) {
        super(size);
    }

    @Override
    protected V getOK(K key) {
        Node<K, V> item = storage.get(key);
        if (item == null) return null;
        if (item.prev == null) return item.value;
        if (item.next == null) {
            tail = item.prev;
            tail.next = null;
        } else {
            item.prev.next = item.next;
            item.next.prev = item.prev;
        }
        item.next = head;
        head = item;
        return item.value;
    }

    @Override
    protected int putOK(K key, V value) {
        if (curSize == maxSize) {
            storage.remove(tail.key);
            tail.key = key;
            tail.value = value;
            storage.put(key, tail);
        }
        if (storage.containsKey(key)) {
            getOK(key);
            Node<K, V> item = storage.get(key);
            item.value = value;
            return 1;
        }
        Node<K, V> item = new Node(key, value);
        item.next = head;
        head = item;
        if (tail == null) tail = item;
        storage.put(key, item);
        curSize++;
        return 1;
    }

}
