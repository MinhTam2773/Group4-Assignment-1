package appDomain;

import shapes.Shape;
import comparators.ShapeComparator;
import utilities.FileHandler;
import utilities.Sorter;

public class AppDriver {

    public static void main(String[] args) {
        Program program = new Program(args);

        if (!program.validateInput()) return;

        String filename = program.getFilename();
        String compareType = program.getCompareType();
        String sortType = program.getSortType();

        // --- Load shapes ---
        Shape[] shapes = FileHandler.parse(filename);
        if (shapes == null || shapes.length == 0) {
            System.out.println("No shapes loaded, exiting...");
            return;
        }

        System.out.println("Compare by: " + program.getCompareTypeName());
        System.out.println("Sort type: " + program.getSortTypeName());
        System.out.println("File: " + filename + "\n");

        ShapeComparator comparator = new ShapeComparator(compareType);
        Sorter sorter = new Sorter(comparator);

        long startTime = System.currentTimeMillis();
        sorter.sortUsing(sortType, shapes);
        long endTime = System.currentTimeMillis();

        for (int i = 0; i < shapes.length; i++) {
            Shape s = shapes[i];
            String valueLabel = "";

            if (compareType.equals("h"))
                valueLabel = "height: " + String.format("%.6f", s.getHeight());
            else if (compareType.equals("a"))
                valueLabel = "area: " + String.format("%.6f", s.calcBaseArea());
            else if (compareType.equals("v"))
                valueLabel = "volume: " + String.format("%.6f", s.calcVolume());

            String elementLabel = null;
            if (i == 0)
                elementLabel = "First element is:";
            else if (i == shapes.length - 1)
                elementLabel = "Last element is:";
            else if ((i + 1) % 1000 == 0)
                elementLabel = (i + 1) + "th element is:";

            if (elementLabel != null) {
                String shapeName = "shape." + s.getClass().getSimpleName();
                System.out.println(String.format("%-20s %-30s %s",
                        elementLabel, shapeName, valueLabel));
            }
        }

        System.out.println("\nSorting completed in " + (endTime - startTime)
                + " ms using " + program.getSortTypeName() + ".");
    }
}
