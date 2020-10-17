public class MyHashMap<K, V> {
    private Entry<K, V>[] buckets;
    private int capacity;

    private int size;
    private double loadFactor = 0.75;


    public MyHashMap(int capacity) {
        this.capacity = capacity;
        buckets = new Entry[capacity];
    }

    public MyHashMap(){ // initial capacity as 16
        this(16);
    }

    public int getSize() {
        return this.size;
    }

    private int getBucketSize() {
        return buckets.length;
    }

    private int getHash(K key) {
        return (key == null) ? 0 : key.hashCode();
    }

    public void put(K key, V value) {
        if (size == loadFactor * capacity) {
            // rehash it
            Entry<K, V>[] old = buckets;

            capacity *= 2;

            buckets = new Entry[capacity];
            for (Entry<K, V> entry : old) {
                while (entry != null) {
                    put(entry.key, entry.value);
                    entry = entry.next;
                }
            }
        }

        Entry<K, V> newEntry = new Entry<K, V>(key, value, null);
        int index = getHash(key) % getBucketSize();

        Entry<K, V> existing = buckets[index];
        if (existing == null) {
            buckets[index] = newEntry;
            size++;
        } else {
            while (existing.next != null) {
                if (existing.key.equals(key)) {
                    existing.value = value;
                    return;
                }
                existing = existing.next;
            }

            if (existing.key.equals(key)) {
                existing.value = value;
            } else {
                existing.next = newEntry;
                size++;
            }
        }

    }

    public V get(K key) {
        int index = getHash(key) % getBucketSize();
        Entry<K, V> existing = buckets[index];

        while (existing != null) {
            if (existing.key.equals(key)) {
                return existing.value;
            }
            existing = existing.next;
        }
        return null;
    }
}
