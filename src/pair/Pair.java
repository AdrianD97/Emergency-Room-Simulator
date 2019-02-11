package pair;

public final class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * getter.
     * @return cheia perechii
     */
    public K getKey() {
        return key;
    }

    /**
     * getter.
     * @return valoarea asociata cheii din perechea curenta
     */
    public V getValue() {
        return value;
    }
}
