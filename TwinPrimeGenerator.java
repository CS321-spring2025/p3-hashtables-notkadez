/**
 * TwinPrimeGenerator.java
 *
 * Provides a public static method generateTwinPrime(int min, int max)
 * that generates a value for the HashTable table size m in the range [min:max].
 * The method finds the smallest twin prime pair in the range and returns
 * the larger prime of the two.
 *
 * @author Kayden Humphries
 */
public class TwinPrimeGenerator {

    /**
     * Checks if a given number is prime.
     *
     * @param n the number to check for primality
     * @return true if the number is prime, false otherwise
     */
    public static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Generates the smallest set of twin primes in the given range [min, max]
     * and returns the larger of the two twin primes.
     *
     * @param min the lower bound of the range (inclusive)
     * @param max the upper bound of the range (inclusive)
     * @return the larger prime number from the first pair of twin primes found in the range
     * @throws IllegalArgumentException if no twin primes are found in the given range
     */
    public static int generateTwinPrime(int min, int max) {
        for (int i = min; i <= max - 2; i++) {
            if (isPrime(i) && isPrime(i + 2)) {
                return i + 2; // Return the larger prime in the twin prime pair
            }
        }
        throw new IllegalArgumentException("No twin primes found in the given range.");
    }

    /**
     * Main method for testing the generateTwinPrime method.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        int min = 95500;
        int max = 96000;
        int twinPrime = generateTwinPrime(min, max);

        System.out.println("The larger twin prime in the range [" + min + ", " + max + "] is: " + twinPrime);
    }
}