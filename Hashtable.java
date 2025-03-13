/**
 * Hashtable.java
 *
 * Abstract class representing a hash table with common insert, search, and utility methods.
 *
 * @author Kayden Humphries
 */
public abstract class Hashtable {

    // Array of HashObject to store keys and associated frequency counts
    protected HashObject[] table;
    protected int size;  // Current number of elements in the table
    protected int capacity;  // Size of the hash table

    /**
     * Constructor to initialize the hash table with a given capacity.
     *
     * @param capacity the capacity of the hash table
     */
    public Hashtable(int capacity) {
        this.capacity = capacity;
        this.table = new HashObject[capacity];
        this.size = 0;
    }

    /**
     * Abstract method to be implemented by subclasses for calculating the primary hash value.
     *
     * @param key the key to hash
     * @return the hash value of the key
     */
    public abstract int h1(Object key);

    /**
     * Abstract method for getting the secondary hash function value (if applicable).
     *
     * @param key the key to hash
     * @return the secondary hash value (used in double hashing)
     */
    public abstract int h2(Object key);

    /**
     * Insert a key into the hash table. If the key already exists, its frequency count is incremented.
     *
     * @param key the key to insert into the table
     */
    public void insert(Object key) {
        int hash = h1(key);
        int stepSize = h2(key);

        HashObject hashObject;
        while ((hashObject = table[hash]) != null && !hashObject.getKey().equals(key)) {
            hashObject.incrementProbeCount();
            hash = (hash + stepSize) % capacity;
        }

        if (table[hash] == null) {
            table[hash] = new HashObject(key);
        } else {
            table[hash].incrementFrequency();
        }

        size++;
    }

    /**
     * Search for a key in the hash table
     *
     * @param key the key to search for
     * @return the HashObject containing the key if found, or null if not found
     */
    public HashObject search(Object key) {
        int hash = h1(key);
        int stepSize = h2(key);

        HashObject hashObject;
        while ((hashObject = table[hash]) != null) {
            hashObject.incrementProbeCount();
            if (hashObject.getKey().equals(key)) {
                return table[hash];
            }
            hash = (hash + stepSize) % capacity;
        }

        return null;  // Not found
    }

    /**
     * Get the current size of the hash table.
     *
     * @return the number of elements in the hash table
     */
    public int getSize() {
        return size;
    }

    /**
     * Get the capacity of the hash table.
     *
     * @return the capacity of the hash table
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Print the hash table contents.
     */
    public void printTable() {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                System.out.println("Index " + i + ": " + table[i].toString());
            } else {
                System.out.println("Index " + i + ": null");
            }
        }
    }

    /**
     * Computes the positive modulus of a dividend and divisor. This method
     * ensures that the result of the modulus operation is always non-negative,
     * even if the dividend is negative.
     *
     * @param dividend the dividend (numerator) of the modulus operation
     * @param divisor the divisor (denominator) of the modulus operation
     * @return the positive modulus result in the range [0, divisor - 1]
     */
    protected int positiveMod(int dividend, int divisor) {
        int quotient = dividend % divisor;
        if (quotient < 0)
            quotient += divisor;
        return quotient;
    }

}