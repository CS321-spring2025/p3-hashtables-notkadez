/**
 * LinearProbing.java
 *
 * Subclass of Hashtable implementing linear probing
 *
 * @author Kayden Humphries
 */
public class LinearProbing extends Hashtable {

    /**
     * Constructor to initialize the hash table with a given capacity
     *
     * @param capacity the capacity of the hash table
     */
    public LinearProbing(int capacity) {
        super(capacity);
    }

    /**
     * Primary hash function for linear probing
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
     * Secondary hash function for linear probing is 1
     *
     * @param key the key to hash
     * @return 1
     */
    @Override
    public int h2(Object key) {
        return 1;
    }
}