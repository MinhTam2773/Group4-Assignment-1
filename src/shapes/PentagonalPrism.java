package shapes;

/**
 * The {@code PentagonalPrism} class models a prism with a pentagonal base.
 * Extending {@link Prism}, it defines methods to compute the base area and volume.
 * <p>
 * Formulas used:
 * <ul>
 *   <li>Base Area = (5 × s² × tan(54°)) / 4</li>
 *   <li>Volume = Base Area × Height</li>
 * </ul>
 */
public class PentagonalPrism extends Prism {

	/**
	 * Constructs a {@code PentagonalPrism} with the specified height and side length.
	 * 
	 * @param height the height of the prism
	 * @param side the side length of the pentagonal base
	 */
	public PentagonalPrism(double height, double side) {
		super(height, side);
	}

	/** 
	 * Calculates the volume of the pentagonal prism.
	 * @return the volume (in cubic units)
	 */
	@Override
	public double calcVolume() {
		return calcBaseArea() * super.getHeight();
	}

	/** 
	 * Calculates the base area of the pentagonal prism.
	 * @return the base area (in square units)
	 */
	@Override
	public double calcBaseArea() {
		return (5 * Math.pow(super.getSide(), 2) * Math.tan(Math.toRadians(54))) / 4;
	}
}
