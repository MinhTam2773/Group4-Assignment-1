package shapes;

/**
 * The {@code TriangularPrism} class models a prism with an equilateral
 * triangular base. It extends {@link Prism} and defines methods to compute
 * both the base area and volume.
 * <p>
 * Formulas used:
 * <ul>
 *   <li>Base Area = (s² × √3) / 4</li>
 *   <li>Volume = Base Area × Height</li>
 * </ul>
 */
public class TriangularPrism extends Prism {

	/**
	 * Constructs a {@code TriangularPrism} with the specified height and side length.
	 *
	 * @param height the height of the prism
	 * @param side the side length of the triangular base
	 */
	public TriangularPrism(double height, double side) {
		super(height, side);
	}

	/**
	 * Calculates the volume of the triangular prism.
	 * Volume = Base Area × Height
	 *
	 * @return the volume (in cubic units)
	 */
	@Override
	public double calcVolume() {
		return calcBaseArea() * super.getHeight();
	}

	/**
	 * Calculates the base area of the equilateral triangular base.
	 * Base Area = (s² × √3) / 4
	 *
	 * @return the base area (in square units)
	 */
	@Override
	public double calcBaseArea() {
		return (Math.pow(super.getSide(), 2) * Math.sqrt(3)) / 4;
	}
}
