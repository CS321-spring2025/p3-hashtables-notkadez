/**
 * Represents the arguments used for configuring an experiment.
 * This class stores the data source type, load factor, and debug level.
 *
 * @author Kayden Humphries
 */
public class ExperimentArguments {
    /** The data source type (1 = random numbers, 2 = dates, 3 = word list) */
    public int dataSource;
    /** The string representation of the data source */
    public String dataSourceString;
    /** The load factor (ratio of objects to table size) */
    public double loadFactor;
    /** The debug level (0 = summary, 1 = summary + dump, 2 = detailed debugging) */
    public int debugLevel;

    /**
     * Private constructor to initialize experiment arguments.
     *
     * @param dataSource The data source type (1, 2, or 3)
     * @param loadFactor The load factor (must be between 0 and 1)
     * @param debugLevel The debug level (0, 1, or 2)
     *
     * @author Kayden Humphries
     */
    private ExperimentArguments(int dataSource, double loadFactor, int debugLevel) {
        this.dataSource = dataSource;
        this.loadFactor = loadFactor;
        this.debugLevel = debugLevel;
        this.dataSourceString = getSourceString(dataSource);
    }

    /**
     * Parses command-line arguments and returns an instance of ExperimentArguments.
     *
     * @param args Command-line arguments: <dataSource> <loadFactor> [<debugLevel>]
     * @return ExperimentArguments instance if valid, otherwise null
     */
    public static ExperimentArguments loadArguments(String[] args) {
        // Validate the number of arguments
        if (args.length < 2 || args.length > 3) {
            printUsage();
            return null;
        }

        // Parse arguments
        int dataSource = Integer.parseInt(args[0]);
        double loadFactor = Double.parseDouble(args[1]);
        int debugLevel = (args.length == 3) ? Integer.parseInt(args[2]) : 0;

        // Validate argument ranges
        boolean validArgs =
                validateRange(dataSource, 1, 3)
                        && validateRange(loadFactor, 0, 1)
                        && validateRange(debugLevel, 0, 2);

        if (!validArgs) {
            printUsage();
            return null;
        }

        return new ExperimentArguments(dataSource, loadFactor, debugLevel);
    }

    /**
     * Prints the usage instructions for the program.
     */
    private static void printUsage() {
        System.err.println("""
                Usage: java HashtableExperiment <dataSource> <loadFactor> [<debugLevel>]
                \t<dataSource>
                \t\t1 ==> random numbers
                \t\t2 ==> dates
                \t\t3 ==> word list
                \t<loadFactor>: The ratio of objects to table size, denoted by α = n/m. Range: [0,1]
                \t<debugLevel> (optional):
                \t\t0 ==> print summary of experiment - default
                \t\t1 ==> same as 0 and dump the two hash tables to files at end
                \t\t2 ==> print debugging output for each insert
                """);
    }

    /**
     * Validates whether a number falls within a given range.
     *
     * @param num The number to validate
     * @param min The minimum allowable value
     * @param max The maximum allowable value
     * @return true if the number is within range, false otherwise
     */
    private static boolean validateRange(double num, double min, double max) {
        return num >= min && num <= max;
    }

    /**
     * Returns the string representation of the given data source type.
     *
     * @param dataSource The data source type (1, 2, or 3)
     * @return The corresponding string representation
     */
    private String getSourceString(int dataSource) {
        return switch (dataSource) {
            case 1 -> "Random Numbers";
            case 2 -> "Dates";
            case 3 -> "Word-List";
            default -> "";
        };
    }
}