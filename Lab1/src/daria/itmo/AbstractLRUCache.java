package daria.itmo;

import java.util.HashMap;
import java.util.Optional;

/**
 * Created by Daria on 10.09.2016.
 */
public abstract class AbstractLRUCache<K, V> {

    HashMap<K, Node<K, V>> storage;
    Node<K, V> head;
    Node<K, V> tail;
    int curSize;
    int maxSize;

    protected AbstractLRUCache() {
        storage = new HashMap<>();
        maxSize = 5000;
        curSize = 0;
    }

    protected AbstractLRUCache(int maxSize) {
        storage = new HashMap<>();
        this.maxSize = maxSize;
        curSize = 0;
    }

    public Optional<V> get(K key) {
        assert key != null;
        V res = getOK(key);
        if (res == null) return Optional.empty();
        assert storage.get(key).value == res;
        Node<K, V> cur = head;
        while (cur != null) {
            assert storage.get(cur.key).value == cur.value;
            cur = cur.next;
        }
        return Optional.of(res);
    }

    public int put(K key, V value) {
        assert key != null;
        assert value != null;
        int res = putOK(key, value);
        assert curSize <= maxSize;
        assert storage.get(key) != null;
        assert storage.get(key).value == value;
        Node<K, V> cur = head;
        while (cur != null) {
            assert storage.get(cur.key).value == cur.value;
            cur = cur.next;
        }
        return res;
    }

    protected abstract V getOK(K key);
    protected abstract int putOK(K key, V value);

}
