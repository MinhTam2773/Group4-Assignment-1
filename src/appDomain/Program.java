package appDomain;

/**
 * The Program class is responsible for interpreting and validating the
 * command-line arguments passed into the program.
 * 
 * It acts as the configuration handler for the entire application, extracting:
 *   - The input file name containing shape data.
 *   - The comparison type (height, area, or volume).
 *   - The sorting algorithm to use (e.g., quick, merge, bubble).
 * 
 * The class also ensures that all user-provided parameters are valid before
 * the sorting operation begins. This prevents runtime errors caused by
 * invalid or missing input.
 */
public class Program {
    // Stores the name of the input file containing the shape list
    private String filename;
    // Comparison type (default is "h" for height)
    private String compareType = "h";
    // Sorting algorithm type (default is "b" for bubble sort)
    private String sortType = "b";

    // Constructor: immediately processes the provided command-line arguments
    public Program(String[] args) {
        parseArgs(args);
    }

    /**
     * Parses command-line arguments and extracts the filename, comparison type,
     * and sorting type. It also handles common input formatting issues, such as
     * different types of dashes or quotation marks.
     */
    private void parseArgs(String[] args) {
        if (args == null || args.length == 0) return;

        for (String arg : args) {
            if (arg == null || arg.isEmpty()) continue;

            // Normalize characters to prevent input errors (e.g., fancy quotes or long dashes)
            arg = arg.replace('–', '-').replace('—', '-')
                     .replace("\"", "").replace("“", "").replace("”", "")
                     .trim().replace("\\", "/");

            String lower = arg.toLowerCase();

            // Skip unneeded parts of the Java command itself
            if (lower.equals("java") || lower.equals("-jar") || lower.endsWith(".jar"))
                continue;

            // Extract argument data depending on prefix (-f, -t, -s)
            if (lower.startsWith("-f")) filename = arg.substring(2).trim();
            else if (lower.startsWith("-t")) compareType = arg.substring(2).trim().toLowerCase();
            else if (lower.startsWith("-s")) sortType = arg.substring(2).trim().toLowerCase();
        }
    }

    /**
     * Validates the parsed command-line inputs to ensure they are valid.
     * Displays helpful error messages for invalid input.
     *
     * @return true if inputs are valid, false if any are invalid.
     */
    public boolean validateInput() {
        // Ensure a filename was provided
        if (filename == null || filename.isEmpty()) {
            System.out.println("❌ Error: No input file provided. Use -f<filename>");
            return false;
        }

        // Ensure comparison type is valid
        if (!isValidCompareType(compareType)) {
            System.out.println("❌ Invalid compare type: " + compareType);
            System.out.println("   Valid options: h (height), v (volume), a (area)");
            return false;
        }

        // Ensure sorting algorithm type is valid
        if (!isValidSortType(sortType)) {
            System.out.println("❌ Invalid sort type: " + sortType);
            System.out.println("   Valid options: q (quick), m (merge), i (insertion), "
                    + "s (selection), h (heap), b (bubble)");
            return false;
        }

        return true;
    }

    // Helper method to check if the compare type is valid
    private boolean isValidCompareType(String t) {
        return t.equals("h") || t.equals("v") || t.equals("a");
    }

    // Helper method to check if the sort type is valid
    private boolean isValidSortType(String t) {
        return t.equals("q") || t.equals("m") || t.equals("i") ||
               t.equals("s") || t.equals("h") || t.equals("b");
    }

    // --- Getters for the parsed values ---
    public String getFilename() { return filename; }
    public String getCompareType() { return compareType; }
    public String getSortType() { return sortType; }

    /**
     * Returns a descriptive name of the selected comparison type for display.
     */
    public String getCompareTypeName() {
        switch (compareType) {
            case "h": return "Height";
            case "a": return "Area";
            case "v": return "Volume";
            default: return "Unknown";
        }
    }

    /**
     * Returns a descriptive name of the selected sorting algorithm for display.
     */
    public String getSortTypeName() {
        switch (sortType) {
            case "q": return "Quick Sort";
            case "m": return "Merge Sort";
            case "i": return "Insertion Sort";
            case "s": return "Selection Sort";
            case "h": return "Heap Sort";
            case "b": return "Bubble Sort";
            default: return "Unknown Sort";
        }
    }
}
