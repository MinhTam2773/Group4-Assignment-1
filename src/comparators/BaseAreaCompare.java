package comparators;
import java.util.Comparator;
import shapes.*;

/**
 * The BaseAreaCompare class implements the Comparator interface and
 * provides a comparison rule for sorting Shape objects based on their
 * base area.
 * 
 * When used in a sorting algorithm, this comparator ensures that shapes
 * are ordered from smallest to largest base area.
 */
public class BaseAreaCompare implements Comparator<Shape> {

    /**
     * Compares two Shape objects by calculating their base area.
     * 
     * @param arg0 the first Shape to compare
     * @param arg1 the second Shape to compare
     * @return a negative integer if arg0 < arg1,
     *         zero if they are equal,
     *         a positive integer if arg0 > arg1
     */
    @Override
    public int compare(Shape arg0, Shape arg1) {
        return Double.compare(arg0.calcBaseArea(), arg1.calcBaseArea());
    }

}
