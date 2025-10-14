package comparators;

import java.util.Comparator;
import shapes.Shape;

public class ShapeComparator implements Comparator<Shape> {
    private String compareBy; // "A" for area, "V" for volume

    public ShapeComparator(String compareBy) {
        this.compareBy = compareBy.toUpperCase();
    }

    @Override
    public int compare(Shape s1, Shape s2) {
        if (compareBy.equals("A")) {
            return Double.compare(s1.calcBaseArea(), s2.calcBaseArea());
        } else if (compareBy.equals("V")) {
            return Double.compare(s1.calcVolume(), s2.calcVolume());
        }
        return 0; // default case
    }
}
