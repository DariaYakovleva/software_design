package daria.itmo;

public class Main {

    public static void main(String[] args) {
        LRUCache<Integer, Integer> myStorage = new LRUCache<>(3);
//        myStorage.put(null, 4);
        myStorage.put(1, 1);
        System.err.println(myStorage.get(1));
        myStorage.put(2, 2);
        System.err.println(myStorage.get(2));
        myStorage.put(3, 3);
        myStorage.put(4, 4);
        System.err.println(myStorage.get(4));
        System.err.println(myStorage.get(1));
    }
}
