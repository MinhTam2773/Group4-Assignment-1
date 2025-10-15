package shapes;

/**
 * The {@code Cylinder} class models a 3D shape with two parallel circular bases
 * connected by a curved surface. It extends {@link Shape} and defines the
 * necessary methods for calculating volume and base area.
 * <p>
 * The formulas used are:
 * <ul>
 *   <li>Volume = π × r² × h</li>
 *   <li>Base Area = π × r²</li>
 * </ul>
 */
public class Cylinder extends Shape {
	private double radius;

	/**
	 * Constructs a {@code Cylinder} object with a specified height and radius.
	 * 
	 * @param height the cylinder’s height
	 * @param radius the radius of its circular base
	 */
	public Cylinder(double height, double radius) {
		super(height);
		this.radius = radius;
	}

	/** 
	 * Computes the volume of the cylinder.
	 * @return the volume (in cubic units)
	 */
	@Override
	public double calcVolume() {
		return Math.PI * Math.pow(radius, 2) * super.getHeight();
	}

	/** 
	 * Computes the base area of the cylinder.
	 * @return the base area (in square units)
	 */
	@Override
	public double calcBaseArea() {
		return Math.PI * Math.pow(radius, 2);
	}
}
