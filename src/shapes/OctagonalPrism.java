package shapes;

public class OctagonalPrism extends Prism
{
	public OctagonalPrism(double height, double side) {
		super(height, side);
	}

	@Override
	public double calcVolume() {
		// Volume = Base Area × Height
		return calcBaseArea() * super.getHeight();
	}

	@Override
	public double calcBaseArea() {
		// Base Area (A) = 2(1 + √2)s²
		return 2 * (1 + Math.sqrt(2)) * Math.pow(super.getSide(), 2);
	}
}
