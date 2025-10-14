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

        // Sort (timer is handled inside Sorter)
        Sorter sorter = new Sorter(comparator);
        sorter.sortUsing(sortType, shapes);

        // Print first, last, and every exact 1000th element
        for (int i = 0; i < shapes.length; i++) {
            Shape s = shapes[i];

            String valueLabel;
            switch (compareType) {
                case "h": valueLabel = "height: " + String.format("%.6f", s.getHeight()); break;
                case "a": valueLabel = "area: " + String.format("%.6f", s.calcBaseArea()); break;
                case "v": valueLabel = "volume: " + String.format("%.6f", s.calcVolume()); break;
                default: valueLabel = "";
            }

            String elementLabel;
            if (i == 0) elementLabel = "First element is:";
            else if (i == shapes.length - 1) elementLabel = "Last element is:";
            else if ((i + 1) % 1000 == 0) elementLabel = (i + 1) + "th element is:";
            else continue; // skip all other elements

            String shapeName = "shape." + s.getClass().getSimpleName();

            System.out.println(String.format("%-20s %-30s %s",
                    elementLabel, shapeName, valueLabel));
        }
    }
}
