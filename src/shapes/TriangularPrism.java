package shapes;

public class TriangularPrism extends Prism
{
	public TriangularPrism(double height, double side) {
		super(height, side);
	}

	@Override
	public double calcVolume() {
		// Volume = Base Area × Height
		return calcBaseArea() * super.getHeight();
	}

	@Override
	public double calcBaseArea() {
		// Base Area (A) = (s²√3) / 4
		return (Math.pow(super.getSide(), 2) * Math.sqrt(3)) / 4;
	}
}
