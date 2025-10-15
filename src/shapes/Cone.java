package shapes;

/**
 * The {@code Cone} class represents a three-dimensional geometric shape
 * that extends the abstract {@link Shape} class.
 * <p>
 * It defines behavior specific to a cone, characterized by a circular base
 * and a pointed top (the apex). The cone’s primary attributes are its
 * height (inherited from {@link Shape}) and radius.
 * <p>
 * This class provides implementations for:
 * <ul>
 *   <li>{@link #calcVolume()} — Calculates the cone’s volume using
 *       the formula: V = (π × r² × h) / 3</li>
 *   <li>{@link #calcBaseArea()} — Calculates the base area using
 *       the formula: A = π × r²</li>
 * </ul>
 */
public class Cone extends Shape {
	private double radius;

	/**
	 * Constructs a {@code Cone} object with a given height and radius.
	 * 
	 * @param height the height of the cone
	 * @param radius the radius of the circular base
	 */
	public Cone(double height, double radius) {
		super(height);
		this.radius = radius;
	}

	/** 
	 * Calculates the volume of the cone.
	 * @return the volume (in cubic units)
	 */
	@Override
	public double calcVolume() {
		return (Math.PI * this.radius * this.radius * super.getHeight()) / 3;
	}

	/** 
	 * Calculates the base area of the cone.
	 * @return the base area (in square units)
	 */
	@Override
	public double calcBaseArea() {
		return Math.PI * this.radius * this.radius;
	}
}
