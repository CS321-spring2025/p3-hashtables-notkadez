/**
 * HashObject.java
 *
 * This class represents a hash object with a generic key, frequency count 
 * (to count duplicates), and a probe count. It overrides the equals and 
 * toString methods, and provides a getKey method to retrieve the key.
 *
 * @author Kayden Humphries
 */
public class HashObject {

    // The key object for this HashObject
    private Object key;

    // Details about the object
    private int frequencyCount;
    private int probeCount;

    /**
     * Constructor to initialize the HashObject with a key.
     *
     * @param key the key for this object
     */
    public HashObject(Object key) {
        this.key = key;
        this.frequencyCount = 1; // initialize frequency to 1
        this.probeCount = 0;     // initialize probe count to 0
    }

    /**
     * Retrieves the key of this HashObject.
     *
     * @return the key
     */
    public Object getKey() {
        return this.key;
    }

    /**
     * Increases the frequency count by 1
     */
    public void incrementFrequency() {
        this.frequencyCount++;
    }

    /**
     * Sets the probe count to the specified count
     *
     * @param probeCount the probeCount to set to
     */
    public void setProbeCount(int probeCount) {
        this.probeCount = probeCount;
    }

    /**
     * Compares this HashObject to another object.
     * The comparison is done based on the key value using the equals method on the keys.
     *
     * @param obj the object to compare with
     * @return true if the keys are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        HashObject other = (HashObject) obj;

        // Use the equals method on the keys to compare
        return this.key.equals(other.key);
    }

    /**
     * Returns a string representation of the HashObject.
     *
     * @return a string representation of the HashObject
     */
    @Override
    public String toString() {
        return key.toString() + " " + frequencyCount + " " + probeCount;
    }
}