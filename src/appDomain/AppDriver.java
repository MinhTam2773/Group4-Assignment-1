package appDomain;

import java.util.Comparator;
import utilities.FileHandler;
import utilities.Sorter;
import shapes.Shape;
import comparators.ShapeComparator;

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
                     .replace("\\", "/");  // convert backslashes to forward slashes

            String lower = arg.toLowerCase();

            // Ignore java, -jar, .jar
            if (lower.equals("java") || lower.equals("-jar") || lower.endsWith(".jar"))
                continue;

            // Case-insensitive -f / -F
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

        // Normalize filename again in case it contains backslashes
        filename = filename.replace("\\", "/");

        // Load shapes
        Shape[] shapes = FileHandler.parse(filename);
        if (shapes == null || shapes.length == 0) {
            System.out.println("❌ No shapes loaded, exiting...");
            return;
        }

        // Select comparator using ShapeComparator
        Comparator<Shape> comp;
        switch (compareType) {
            case "v": comp = new ShapeComparator("V"); break;
            case "a": comp = new ShapeComparator("A"); break;
            case "h":
            default:  comp = Comparator.naturalOrder(); break; // default: height
        }

        System.out.println("📊 Compare by: " + compareType.toUpperCase());
        System.out.println("⚙️ Sort type: " + sortType.toUpperCase());
        System.out.println("📂 File: " + filename);

        // Sort and benchmark
        long start = System.currentTimeMillis();
        switch (sortType) {
//            case "b": Sorter.bubbleSort(shapes, comp); break;
//            case "i": Sorter.insertionSort(shapes, comp); break;
//            case "s": Sorter.selectionSort(shapes, comp); break;
//            case "m": Sorter.mergeSort(shapes, comp); break;
//            case "q": Sorter.quickSort(shapes, comp); break;
//            case "h":
//            case "z": Sorter.heapSort(shapes, comp); break;
//            default:
//                System.out.println("⚠️ Invalid sort type, using Bubble Sort.");
//                Sorter.bubbleSort(shapes, comp);
        }
        long end = System.currentTimeMillis();

        System.out.println("⏱️ Sorting completed in " + (end - start) + " ms\n");

        // Print first, last, and every 1000th shape for verification
        for (int i = 0; i < shapes.length; i++) {
            if (i == 0 || i == shapes.length - 1 || i % 1000 == 0) {
                System.out.println(i + ": " + shapes[i]);
            }
        }
    }
}
