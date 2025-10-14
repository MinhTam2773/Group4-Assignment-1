package comparators;
import shapes.Shape;
import java.util.Comparator;

/**
 * This class allows Shape objects to be compared based on the chosen property.
 * 
 * h - height
 * a - base area
 * v - volume
 */
public class ShapeComparator implements Comparator<Shape>{
	public static final String HEIGHT = "h";
	public static final String BASE_AREA = "a";
	public static final String VOLUME = "v";
	
	//stores the comparison selected (h, a, or v)
	private String compareType;
	
	/**
	 * Accepts the argument on how the shape should be compared
	 * 
	 * @param compareType ('h', 'a', or 'v')
	 */
	public ShapeComparator(String compareType) {
		this.compareType = compareType.toLowerCase();
	}
	
	/**
	 * Compares the two shape objects based on specified option
	 * returns negative int if s1 is less than s2
	 * zero if s1 is equal to s2
	 * and negative int if s1 is greater then s2
	 * 
	 * @param s1 the first Shape object
	 * @param s2 the second Shape object
	 * @return an integer indicating the comparison result
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
			throw new IllegalArgumentException("Invalid comparison type: " + compareType);
		}
	}
}