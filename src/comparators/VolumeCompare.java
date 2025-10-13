package comparators;

import java.util.Comparator;
import shapes.Shape;

public class VolumeCompare implements Comparator<Shape> {

    @Override
    public int compare(Shape s1, Shape s2) {
        double v1 = s1.calcVolume();
        double v2 = s2.calcVolume();

        // Return negative if s1 > s2 to sort descending
        if (v1 > v2) {
            return -1;
        } else if (v1 < v2) {
            return 1;
        } else {
            return 0;
        }
    }
}
