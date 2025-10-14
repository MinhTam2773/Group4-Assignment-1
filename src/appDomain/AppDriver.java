package appDomain;

import shapes.Shape;
import comparators.ShapeComparator;
import utilities.FileHandler;
import utilities.Sorter;

public class AppDriver {

    public static void main(String[] args) {
        String filename = null;
        String compareType = "h"; // default: height
        String sortType = "b";    // default: bubble sort

        // Parse command-line arguments
        for (String arg : args) {
            if (arg == null || arg.isEmpty()) continue;
            arg = arg.replace('–', '-').replace('—', '-')
                     .replace("\"", "").replace("“", "").replace("”", "").trim()
                     .replace("\\", "/");

            String lower = arg.toLowerCase();
            if (lower.equals("java") || lower.equals("-jar") || lower.endsWith(".jar"))
                continue;

            if (lower.startsWith("-f")) filename = arg.substring(2).trim();
            else if (lower.startsWith("-t")) compareType = arg.substring(2).trim().toLowerCase();
            else if (lower.startsWith("-s")) sortType = arg.substring(2).trim().toLowerCase();
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

        // Sort and benchmark
        Sorter sorter = new Sorter(comparator);
        long start = System.currentTimeMillis();
        sorter.sortUsing(sortType, shapes);
        long end = System.currentTimeMillis();

        // Determine label based on compareType
        String label;
        switch (compareType) {
            case "h": label = "height"; break;
            case "a": label = "area"; break;
            case "v": label = "volume"; break;
            default: label = "value"; break;
        }

        // Print first, last, and every 1000th element
        for (int i = 0; i < shapes.length; i++) {
            if (i == 0 || i == shapes.length - 1 || i % 1000 == 0) {
                Shape s = shapes[i];
                double value;
                switch (compareType) {
                    case "h": value = s.getHeight(); break;
                    case "a": value = s.calcBaseArea(); break;
                    case "v": value = s.calcVolume(); break;
                    default: value = s.getHeight(); break;
                }
                String ordinal = getOrdinal(i + 1);
                System.out.println(ordinal + " element is: " 
                                   + s.getClass().getSimpleName() + " " + label + ": " + value);
            }
        }

        // Print benchmark
        System.out.println("\n⏱️ Sorting completed in " + (end - start) + " ms using " 
                           + (sortType.equals("b") ? "Bubble Sort" : "Selected Algorithm"));
    }

    // Helper to get ordinal suffix
    private static String getOrdinal(int number) {
        if (number % 100 >= 11 && number % 100 <= 13) {
            return number + "th";
        }
        switch (number % 10) {
            case 1: return number + "st";
            case 2: return number + "nd";
            case 3: return number + "rd";
            default: return number + "th";
        }
    }
}
