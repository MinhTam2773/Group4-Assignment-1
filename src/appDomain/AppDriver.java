package appDomain;

import shapes.Shape;
import comparators.ShapeComparator;
import utilities.FileHandler;
import utilities.Sorter;

/**
 * The AppDriver class serves as the main entry point of the Shape Sorting Program.
 * 
 * It coordinates the entire process of:
 *  1. Reading command-line arguments through the Program class.
 *  2. Loading Shape objects from an input file using FileHandler.
 *  3. Sorting the shapes using a chosen algorithm and comparison type.
 *  4. Displaying key results, including the first, last, and every 1000th element.
 *  5. Reporting the total time taken to perform the sorting operation.
 * 
 * This file essentially ties all the major system components together — it acts as
 * the central controller of the application.
 */
public class AppDriver {

    public static void main(String[] args) {
        // Initialize a Program instance to interpret command-line input
        Program program = new Program(args);

        // Validate input; exit if arguments are missing or invalid
        if (!program.validateInput()) return;

        // Retrieve command-line parameters from Program
        String filename = program.getFilename();
        String compareType = program.getCompareType();
        String sortType = program.getSortType();

        // --- Load shape data from the input file ---
        // FileHandler is responsible for reading and parsing shape objects from file
        Shape[] shapes = FileHandler.parse(filename);

        // If file is empty or could not be parsed, stop execution
        if (shapes == null || shapes.length == 0) {
            System.out.println("No shapes loaded, exiting...");
            return;
        }

        // Display program configuration to the user
        System.out.println("Compare by: " + program.getCompareTypeName());
        System.out.println("Sort type: " + program.getSortTypeName());
        System.out.println("File: " + filename + "\n");

        // Create a ShapeComparator to define the comparison rule (height, area, or volume)
        ShapeComparator comparator = new ShapeComparator(compareType);

        // Create a Sorter object that will handle sorting using the given comparator
        Sorter sorter = new Sorter(comparator);

        // Record start time for performance measurement
        long startTime = System.currentTimeMillis();

        // Perform the sorting using the specified algorithm
        sorter.sortUsing(sortType, shapes);

        // Record end time after sorting completes
        long endTime = System.currentTimeMillis();

        // Iterate through all shapes to display selected records (first, last, and every 1000th)
        for (int i = 0; i < shapes.length; i++) {
            Shape s = shapes[i];
            String valueLabel = "";

            // Format the value shown (height, area, or volume) with six decimal precision
            if (compareType.equals("h"))
                valueLabel = "height: " + String.format("%.6f", s.getHeight());
            else if (compareType.equals("a"))
                valueLabel = "area: " + String.format("%.6f", s.calcBaseArea());
            else if (compareType.equals("v"))
                valueLabel = "volume: " + String.format("%.6f", s.calcVolume());

            // Determine if this element should be printed
            String elementLabel = null;
            if (i == 0)
                elementLabel = "First element is:";
            else if (i == shapes.length - 1)
                elementLabel = "Last element is:";
            else if ((i + 1) % 1000 == 0)
                elementLabel = (i + 1) + "th element is:";

            // Print details only for selected elements
            if (elementLabel != null) {
                String shapeName = "shape." + s.getClass().getSimpleName();
                System.out.println(String.format("%-20s %-30s %s",
                        elementLabel, shapeName, valueLabel));
            }
        }

        // Display sorting duration and algorithm used
        System.out.println("\nSorting completed in " + (endTime - startTime)
                + " ms using " + program.getSortTypeName() + ".");
    }
}
