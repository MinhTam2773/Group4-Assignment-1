package shapes;

/**
 * The {@code Shape} class is an abstract base class that defines a generic
 * 3D shape characterized by its height. It provides a foundation for
 * all specific shape types (like cones, prisms, and pyramids).
 * <p>
 * Subclasses must implement:
 * <ul>
 *   <li>{@link #calcVolume()} — for computing shape volume</li>
 *   <li>{@link #calcBaseArea()} — for computing the base area</li>
 * </ul>
 * <p>
 * Implements {@link Comparable} so that shapes can be compared by height
 * by default.
 */
public abstract class Shape implements Comparable<Shape> {
	private double height;

	/**
	 * Abstract method to calculate the shape’s volume.
	 * 
	 * @return the volume (in cubic units)
	 */
	public abstract double calcVolume();

	/**
	 * Abstract method to calculate the shape’s base area.
	 * 
	 * @return the base area (in square units)
	 */
	public abstract double calcBaseArea();

	/**
	 * Constructs a {@code Shape} with a specified height.
	 * 
	 * @param height the height of the shape
	 */
	public Shape(double height) {
		this.height = height;
	}

	/**
	 * Gets the shape’s height.
	 * 
	 * @return the height
	 */
	public double getHeight() {
		return height;
	}

	/**
	 * Compares two shapes based on height. 
	 * Returns:
	 * <ul>
	 *   <li>1 if this shape is taller</li>
	 *   <li>-1 if shorter</li>
	 *   <li>0 if equal height</li>
	 * </ul>
	 */
	@Override
	public int compareTo(Shape that) {
		if (this.height > that.height) return 1;
		else if (this.height < that.height) return -1;
		else return 0;
	}
}
