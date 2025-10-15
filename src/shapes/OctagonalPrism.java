package shapes;

/**
 * The {@code OctagonalPrism} class represents a prism with an octagonal base.
 * It inherits height and side properties from {@link Prism} and implements
 * methods for calculating the base area and volume.
 * <p>
 * Formulas used:
 * <ul>
 *   <li>Base Area = 2 × (1 + √2) × s²</li>
 *   <li>Volume = Base Area × Height</li>
 * </ul>
 */
public class OctagonalPrism extends Prism {

	/**
	 * Constructs an {@code OctagonalPrism} with a given height and side length.
	 * 
	 * @param height the height of the prism
	 * @param side the side length of the octagonal base
	 */
	public OctagonalPrism(double height, double side) {
		super(height, side);
	}

	/** 
	 * Calculates the volume of the octagonal prism.
	 * @return the volume (in cubic units)
	 */
	@Override
	public double calcVolume() {
		return calcBaseArea() * super.getHeight();
	}

	/** 
	 * Calculates the base area of the octagonal prism.
	 * @return the base area (in square units)
	 */
	@Override
	public double calcBaseArea() {
		return 2 * (1 + Math.sqrt(2)) * Math.pow(super.getSide(), 2);
	}
}
