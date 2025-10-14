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

<<<<<<< HEAD
            if (lower.startsWith("-f")) filename = arg.substring(2).trim();
            else if (lower.startsWith("-t")) compareType = arg.substring(2).trim().toLowerCase();
            else if (lower.startsWith("-s")) sortType = arg.substring(2).trim().toLowerCase();
=======
            if (lower.startsWith("-f")) {
                filename = arg.substring(2).trim();
            } else if (lower.startsWith("-t")) {
                compareType = arg.substring(2).trim().toLowerCase();
            } else if (lower.startsWith("-s")) {
                sortType = arg.substring(2).trim().toLowerCase();
            }
>>>>>>> branch 'Alex_R' of https://github.com/MinhTam2773/Group4-Assignment-1.git
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

<<<<<<< HEAD
=======
        // Create comparator
        ShapeComparator comparator;
        switch (compareType) {
            case "v": comparator = new ShapeComparator("V"); break;
            case "a": comparator = new ShapeComparator("A"); break;
            case "h":
            default:  comparator = new ShapeComparator("H"); break; // default: height
        }

>>>>>>> branch 'Alex_R' of https://github.com/MinhTam2773/Group4-Assignment-1.git
        System.out.println("📊 Compare by: " + compareType.toUpperCase());
        System.out.println("⚙️ Sort type: " + sortType.toUpperCase());
        System.out.println("📂 File: " + filename);

<<<<<<< HEAD
        // Create comparator
        ShapeComparator comparator = new ShapeComparator(compareType);

=======
>>>>>>> branch 'Alex_R' of https://github.com/MinhTam2773/Group4-Assignment-1.git
        // Sort
        Sorter sorter = new Sorter(comparator);
        long start = System.currentTimeMillis();
        sorter.sortUsing(sortType, shapes);
        long end = System.currentTimeMillis();

        // Print elements with proper suffix and value
        for (int i = 0; i < shapes.length; i++) {
            int pos = i + 1;
            String suffix;
            if (pos % 10 == 1 && pos % 100 != 11) suffix = "st";
            else if (pos % 10 == 2 && pos % 100 != 12) suffix = "nd";
            else if (pos % 10 == 3 && pos % 100 != 13) suffix = "rd";
            else suffix = "th";

            Shape s = shapes[i];
            String value = "";
            switch (compareType) {
                case "h": value = "height: " + s.getHeight(); break;
                case "a": value = "area: " + s.calcBaseArea(); break;
                case "v": value = "volume: " + s.calcVolume(); break;
            }

            System.out.println(pos + suffix + " element is: " + s.getClass().getSimpleName() + " " + value);
        }

        // Show benchmark with full sort name
        System.out.println("\n⏱️ Sorting completed in " + (end - start) + " ms using " 
                            + sorter.getSortName(sortType) + ".");
    }
}
