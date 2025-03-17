/**
 * DoubleHashing.java
 *
 * Subclass of Hashtable implementing double hashing
 *
 * @author Kayden Humphries
 */
public class DoubleHashing extends Hashtable {

    /**
     * Constructor to initialize the hash table with a given capacity
     *
     * @param capacity the capacity of the hash table
     */
    public DoubleHashing(int capacity) {
        super(capacity);
    }

    /**
     * Primary hash function for double hashing
     * h1(k) = k mod m, where k is the key and m is capacity of table
     *
     * @param key the key to hash
     * @return the hash value of the key
     */
    @Override
    public int h1(Object key) {
        return positiveMod(key.hashCode(), capacity);
    }

    /**
     * Secondary hash function for double hashing
     * h2(k) = 1 + k mod (m - 2), where k is the key and m is capacity of table
     *
     * @param key the key to hash
     * @return the secondary hash value
     */
    @Override
    public int h2(Object key) {
        return 1 + positiveMod(key.hashCode(), capacity - 2);
    }
}