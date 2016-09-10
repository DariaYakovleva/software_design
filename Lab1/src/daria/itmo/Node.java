package daria.itmo;

import java.util.Iterator;

/**
 * Created by Daria on 10.09.2016.
 */
public class Node<K, V> {

    K key;
    V value;
    Node<K, V> prev;
    Node<K, V> next;

    public Node() {
    }

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        prev = null;
        next = null;
    }
}
