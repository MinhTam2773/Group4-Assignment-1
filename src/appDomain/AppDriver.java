package appDomain;

import shapes.Shape;
import comparators.ShapeComparator;
import utilities.FileHandler;
import utilities.Sorter;

public class AppDriver {

    public static void main(String[] args) {
        String filename = null;
        String compareType = "h"; // default: compare by height
        String sortType = "b";    // default: bubble sort

        // Parse command-line arguments
        for (String arg : args) {
            if (arg == null || arg.isEmpty()) continue;

            // Normalize dashes/quotes and Windows paths
            arg = arg.replace('–', '-').replace('—', '-')
                     .replace("\"", "").replace("“", "").replace("”", "").trim()
                     .replace("\\", "/");

            String lower = arg.toLowerCase();

            // Ignore java, -jar, .jar
            if (lower.equals("java") || lower.equals("-jar") || lower.endsWith(".jar"))
                continue;

            if (lower.startsWith("-f")) {
                filename = arg.substring(2).trim();
            } else if (lower.startsWith("-t")) {
                compareType = arg.substring(2).trim().toLowerCase();
            } else if (lower.startsWith("-s")) {
                sortType = arg.substring(2).trim().toLowerCase();
            }
        }

        if (filename == null || filename.isEmpty()) {
            System.out.println("❌ Error: No input file provided. Use -f<filename>");
            return;
        }

        filename = filename.replace("\\", "/");

        // Load shapes
        Shape[] shapes = FileHandler.parse(filename);
        if (shapes == null || shapes.length == 0) {
            System.out.println("❌ No shapes loaded, exiting...");
            return;
        }

        System.out.println("📊 Compare by: " + compareType.toUpperCase());
        System.out.println("⚙️ Sort type: " + sortType.toUpperCase());
        System.out.println("📂 File: " + filename);

        // Create comparator
        ShapeComparator comparator = new ShapeComparator(compareType);
        
        // Sort
        Sorter sorter = new Sorter(comparator);
        sorter.sortUsing(sortType, shapes);

        // Print first, last, and every 1000th shape for verification
        for (int i = 0; i < shapes.length; i++) {
            if (i == 0 || i == shapes.length - 1 || i % 1000 == 0) {
                System.out.println(i + ": " + shapes[i]);
            }
        }
    }
}