package comparators;

import java.util.Comparator;
import shapes.Shape;

/**
 * The VolumeCompare class implements the Comparator interface to sort
 * Shape objects based on their volume.
 * 
 * This comparator is unique because it sorts in **descending order** —
 * meaning larger-volume shapes appear first.
 * 
 * It is used when the user specifically wants to view or process shapes
 * from largest to smallest volume.
 */
public class VolumeCompare implements Comparator<Shape> {

    /**
     * Compares two Shape objects based on their calculated volume.
     * Returns values that produce descending order results.
     *
     * @param s1 first shape
     * @param s2 second shape
     * @return -1 if s1 > s2 (so s1 appears before s2),
     *          1 if s1 < s2,
     *          0 if volumes are equal
     */
    @Override
    public int compare(Shape s1, Shape s2) {
        double v1 = s1.calcVolume();
        double v2 = s2.calcVolume();

        // Custom descending logic — reverse normal comparison order
        if (v1 > v2) {
            return -1;
        } else if (v1 < v2) {
            return 1;
        } else {
            return 0;
        }
    }
}
