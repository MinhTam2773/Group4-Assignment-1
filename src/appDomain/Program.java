package appDomain;

public class Program {
    private String filename;
    private String compareType = "h"; // default: height
    private String sortType = "b";    // default: bubble

    public Program(String[] args) {
        parseArgs(args);
    }

    private void parseArgs(String[] args) {
        if (args == null || args.length == 0) return;

        for (String arg : args) {
            if (arg == null || arg.isEmpty()) continue;

            arg = arg.replace('–', '-').replace('—', '-')
                     .replace("\"", "").replace("“", "").replace("”", "")
                     .trim().replace("\\", "/");

            String lower = arg.toLowerCase();
            if (lower.equals("java") || lower.equals("-jar") || lower.endsWith(".jar"))
                continue;

            if (lower.startsWith("-f")) filename = arg.substring(2).trim();
            else if (lower.startsWith("-t")) compareType = arg.substring(2).trim().toLowerCase();
            else if (lower.startsWith("-s")) sortType = arg.substring(2).trim().toLowerCase();
        }
    }

    public boolean validateInput() {
        if (filename == null || filename.isEmpty()) {
            System.out.println("❌ Error: No input file provided. Use -f<filename>");
            return false;
        }

        if (!isValidCompareType(compareType)) {
            System.out.println("❌ Invalid compare type: " + compareType);
            System.out.println("   Valid options: h (height), v (volume), a (area)");
            return false;
        }

        if (!isValidSortType(sortType)) {
            System.out.println("❌ Invalid sort type: " + sortType);
            System.out.println("   Valid options: q (quick), m (merge), i (insertion), "
                    + "s (selection), h (heap), b (bubble)");
            return false;
        }

        return true;
    }

    private boolean isValidCompareType(String t) {
        return t.equals("h") || t.equals("v") || t.equals("a");
    }

    private boolean isValidSortType(String t) {
        return t.equals("q") || t.equals("m") || t.equals("i") ||
               t.equals("s") || t.equals("h") || t.equals("b");
    }

    public String getFilename() {
        return filename;
    }

    public String getCompareType() {
        return compareType;
    }

    public String getSortType() {
        return sortType;
    }

    // Human-readable compare type name
    public String getCompareTypeName() {
        switch (compareType) {
            case "h": return "Height";
            case "a": return "Area";
            case "v": return "Volume";
            default: return "Unknown";
        }
    }

    // Human-readable sort type name
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
