import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Class to conduct experiments with open-addressing hash tables using linear probing
 * and double hashing. Ensures consistency in data for fair comparisons.
 * <p>
 * Supports three data sources: random integers, dates, and words from a file.
 * Also implements three debug levels:
 * <ul>
 *   <li>Level 0: Summary output</li>
 *   <li>Level 1: Dumps hash table to a file</li>
 *   <li>Level 2: Prints each inserted element</li>
 * </ul>
 * </p>
 *
 * @author Kayden Humphries
 */
public class HashtableExperiment {
    private final ExperimentArguments experimentArguments;
    private final int tableSize;
    private final int targetSize;

    /**
     * Initializes the experiment, generating a twin prime table size and preloading data.
     *
     * @param args Command-line arguments encapsulated in an ExperimentArguments object
     */
    public HashtableExperiment(ExperimentArguments args) {
        this.experimentArguments = args;
        this.tableSize = TwinPrimeGenerator.generateTwinPrime(95500, 96000);
        this.targetSize = (int) Math.ceil(tableSize * experimentArguments.loadFactor);

        printArgumentInfo();

        long currentTime = new Date().getTime();
        runExperiment(new LinearProbing(tableSize), "Linear Probing", "linear-dump.txt", currentTime);
        runExperiment(new DoubleHashing(tableSize), "Double Hashing", "double-dump.txt", currentTime);
    }

    /**
     * Prints information about the experiment's parameters.
     */
    private void printArgumentInfo() {
        System.out.println("HashtableExperiment: Found a twin prime for table capacity: " + this.tableSize);
        System.out.printf("HashtableExperiment: Input: %-11s Loadfactor: %.2f\n",
                this.experimentArguments.dataSourceString,
                this.experimentArguments.loadFactor);
    }

    /**
     * Runs the experiment for a given hash table type and prints results.
     *
     * @param table         The hash table instance (either LinearProbing or DoubleHashing)
     * @param method        A string representing the hashing method used
     * @param filename      The filename for dumping hash table data if debug level is 1
     * @param currentTime   The current time to be used as a seed
     */
    private void runExperiment(Hashtable table, String method, String filename, long currentTime) {
        System.out.println("\n\t\tUsing " + method);
        int count = loadData(table, targetSize, currentTime);

        printAnalytics(table, count);

        if (experimentArguments.debugLevel == 1) {
            table.dumpToFile(filename);
            System.out.println("HashtableExperiment: Saved dump of hash table to " + filename);
        }
    }

    /**
     * Prints analytics of the hash table experiment, including insert count and probe count.
     *
     * @param table The hash table used in the experiment
     * @param count Total number of insertions attempted
     */
    private void printAnalytics(Hashtable table, int count) {
        System.out.printf("HashtableExperiment: size of hash table is: %d\n", targetSize);
        int duplicates = count - targetSize; // Keeping this as per user preference
        System.out.printf("\t\tInserted %d elements, of which %d were duplicates\n", count, duplicates);
        System.out.printf("\t\tAvg. no. of probes = %.2f\n", (double) table.getProbeCount() / table.getSize());
    }

    /**
     * Loads data into the hash table based on the specified data source.
     *
     * @param table       The hash table to insert data into
     * @param targetSize  The target number of elements to insert
     * @param currentTime The current time to be used as a seed
     * @return The total number of insertions attempted
     */
    private int loadData(Hashtable table, int targetSize, long currentTime) {
        return switch (experimentArguments.dataSource) {
            case 1 -> loadRandomIntegers(table, targetSize, currentTime);
            case 2 -> loadDates(table, targetSize, currentTime);
            case 3 -> loadWordList(table, targetSize);
            default -> throw new IllegalArgumentException("Invalid data source: " + experimentArguments.dataSource);
        };
    }

    /**
     * Inserts random integers into the hash table.
     *
     * @param table      The hash table to insert into
     * @param targetSize The number of elements to insert
     * @param seed       The seed to generate random numbers
     * @return The total number of insertions attempted
     */
    private int loadRandomIntegers(Hashtable table, int targetSize, long seed) {
        Random rand = new Random(seed);
        int count = 0;
        while (table.getSize() < targetSize) {
            int key = rand.nextInt();
            insert(table, key);
            count++;
        }
        return count;
    }

    /**
     * Inserts Date objects into the hash table.
     *
     * @param table       The hash table to insert into
     * @param targetSize  The number of elements to insert
     * @param currentTime The starting date
     * @return The total number of insertions attempted
     */
    private int loadDates(Hashtable table, int targetSize, long currentTime) {
        int count = 0;
        Date date = new Date(currentTime);
        while (table.getSize() < targetSize) {
            insert(table, date);
            currentTime += 1000;
            date = new Date(currentTime);
            count++;
        }
        return count;
    }

    /**
     * Reads words from "word-list.txt" and inserts them into the hash table.
     *
     * @param table      The hash table to insert words into
     * @param targetSize The number of words to insert
     * @return The total number of insertions attempted
     */
    private int loadWordList(Hashtable table, int targetSize) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File("word-list.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error: word-list.txt not found.");
        }

        int count = 0;
        while (scanner.hasNextLine() && table.getSize() < targetSize) {
            String word = scanner.nextLine();
            insert(table, word);
            count++;
        }
        scanner.close();
        return count;
    }

    /**
     * Inserts given key into provided hashtable and outputs debug information if requested
     *
     * @param table the table to insert into
     * @param key   the key to be inserted
     */
    private void insert(Hashtable table, Object key) {
        int size = table.getSize();
        int pos = table.insert(key);

        if (this.experimentArguments.debugLevel == 2) {
            boolean duplicate = size == table.getSize(); // If the table size stays the same, the item was a duplicate

            if (duplicate) {
                System.out.printf("Found duplicate element \"%s\" at position %d\n", key.toString(), pos);
            } else {
                System.out.printf("Inserted \"%s\" at position %d\n", key.toString(), pos);
            }
        }
    }

    /**
     * Main method to run the experiment based on command-line arguments.
     *
     * @param args Command-line arguments specifying data source, load factor, and debug level
     */
    public static void main(String[] args) {
        ExperimentArguments arguments = ExperimentArguments.loadArguments(args);
        if (arguments == null) return;

        new HashtableExperiment(arguments);
    }
}