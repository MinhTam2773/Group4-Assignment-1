package appDomain;

import java.io.File;
import java.util.Comparator;
import utilities.FileHandler;
import utilities.Sorter;
import shapes.Shape;
import comparators.BaseAreaCompare;
import comparators.VolumeCompare;

public class AppDriver {

    public static void main(String[] args) {
        String filename = null;
        String compareType = "h"; // default compare by height
        String sortType = "b";    // default bubble sort

        for (String arg : args) {
            if (arg == null || arg.isEmpty()) continue;

            // Normalize: handle weird dashes/quotes
            arg = arg.replace('–', '-').replace('—', '-')
                     .replace("\"", "").replace("“", "").replace("”", "").trim();

            String lower = arg.toLowerCase();

            // Ignore things like "java", "-jar", or ".jar"
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

        // Handle relative vs absolute file path
        File file = new File(filename);
        if (!file.exists()) {
            // Try resolving relative to project root
            file = new File(System.getProperty("user.dir"), filename);
            filename = file.getAbsolutePath();
        }

        // Load shapes
        Shape[] shapes = FileHandler.parse(filename);
        if (shapes == null || shapes.length == 0) {
            System.out.println("❌ No shapes loaded, exiting...");
            return;
        }

        // Select comparator
        Comparator<Shape> comp;
        switch (compareType) {
            case "v": comp = new VolumeCompare(); break;
            case "a": comp = new BaseAreaCompare(); break;
            case "h": 
            default:  comp = Comparator.naturalOrder(); break;
        }

        System.out.println("\n📊 Compare by: " + compareType.toUpperCase());
        System.out.println("⚙️ Sort type: " + sortType.toUpperCase());
        System.out.println("📂 File: " + filename);

        // Sort and benchmark
        long start = System.currentTimeMillis();
        switch (sortType) {
            case "b": Sorter.bubbleSort(shapes, comp); break;
            case "i": Sorter.insertionSort(shapes, comp); break;
            case "s": Sorter.selectionSort(shapes, comp); break;
            case "m": Sorter.mergeSort(shapes, comp); break;
            case "q": Sorter.quickSort(shapes, comp); break;
            case "h":
            case "z": Sorter.heapSort(shapes, comp); break;
            default:
                System.out.println("⚠️ Invalid sort type, using Bubble Sort.");
                Sorter.bubbleSort(shapes, comp);
        }
        long end = System.currentTimeMillis();

        System.out.println("\n⏱️ Sorting completed in " + (end - start) + " ms\n");

        for (int i = 0; i < shapes.length; i++) {
            if (i == 0 || i == shapes.length - 1 || i % 1000 == 0) {
                System.out.println(i + ": " + shapes[i]);
            }
        }
    }
}
