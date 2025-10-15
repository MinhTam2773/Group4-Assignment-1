package shapes;

/**
 * The {@code Prism} class serves as an abstract base for all prism-like shapes.
 * <p>
 * A prism is a solid object with two parallel, identical polygonal bases.
 * Each subclass (e.g., {@link TriangularPrism}, {@link PentagonalPrism})
 * defines its own way to calculate base area and volume.
 */
public abstract class Prism extends Shape {
	private double side;

	/**
	 * Constructs a {@code Prism} with a specified height and side length.
	 * 
	 * @param height the height of the prism
	 * @param side the side length of the base polygon
	 */
	public Prism(double height, double side) {
		super(height);
		this.side = side;
	}

	/**
	 * Gets the side length of the base polygon.
	 * 
	 * @return the side length
	 */
	public double getSide() {
		return side;
	}
}
