package shapes;

/**
 * The {@code SquarePrism} class represents a three-dimensional prism
 * with a square base. It inherits from {@link Prism}, utilizing the
 * base class’s side and height attributes.
 * <p>
 * Formulas used:
 * <ul>
 *   <li>Base Area = s²</li>
 *   <li>Volume = Base Area × Height = s² × h</li>
 * </ul>
 * This is a relatively simple shape because both area and volume rely
 * on basic square geometry.
 */
public class SquarePrism extends Prism {

	/**
	 * Constructs a {@code SquarePrism} object with a given height and side length.
	 *
	 * @param height the height of the prism
	 * @param side the length of one side of the square base
	 */
	public SquarePrism(double height, double side) {
		super(height, side);
	}

	/**
	 * Calculates the volume of the square prism using the formula:
	 * V = s² × h
	 *
	 * @return the volume (in cubic units)
	 */
	@Override
	public double calcVolume() {
		return super.getSide() * super.getSide() * super.getHeight();
	}

	/**
	 * Calculates the base area of the square prism using the formula:
	 * A = s²
	 *
	 * @return the base area (in square units)
	 */
	@Override
	public double calcBaseArea() {
		return super.getSide() * super.getSide();
	}
}
