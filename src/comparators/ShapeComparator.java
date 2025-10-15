package comparators;

import shapes.Shape;
import java.util.Comparator;

/**
 * The ShapeComparator class is a flexible comparator that allows Shape objects
 * to be compared based on one of three selectable attributes:
 * 
 *   - Height ("h")
 *   - Base Area ("a")
 *   - Volume ("v")
 * 
 * This comparator is used dynamically depending on user input, allowing
 * the same sorting logic to be reused for multiple metrics.
 */
public class ShapeComparator implements Comparator<Shape> {
    // Constants for comparison modes
    public static final String HEIGHT = "h";
    public static final String BASE_AREA = "a";
    public static final String VOLUME = "v";

    // Stores which property the comparator will use for comparison
    private String compareType;

    /**
     * Constructor that defines the comparison mode based on user selection.
     *
     * @param compareType 'h' for height, 'a' for base area, or 'v' for volume
     */
    public ShapeComparator(String compareType) {
        this.compareType = compareType.toLowerCase();
    }

    /**
     * Compares two Shape objects using the property specified in compareType.
     *
     * @param s1 first shape to compare
     * @param s2 second shape to compare
     * @return an integer indicating comparison result (-1, 0, or 1)
     */
    @Override
    public int compare(Shape s1, Shape s2) {
        switch (compareType) {
            case HEIGHT:
                return Double.compare(s1.getHeight(), s2.getHeight());
            case BASE_AREA:
                return Double.compare(s1.calcBaseArea(), s2.calcBaseArea());
            case VOLUME:
                return Double.compare(s1.calcVolume(), s2.calcVolume());
            default:
                // If user input is invalid, throw an exception for debugging
                throw new IllegalArgumentException("Invalid comparison type: " + compareType);
        }
    }
}
