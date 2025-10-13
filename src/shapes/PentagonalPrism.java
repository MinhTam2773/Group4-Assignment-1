package shapes;

public class PentagonalPrism extends Prism
{
	public PentagonalPrism(double height, double side) {
		super(height, side);
	}

	@Override
	public double calcVolume() {
		// Volume = Base Area × Height
		return calcBaseArea() * super.getHeight();
	}

	@Override
	public double calcBaseArea() {
		// Base Area (A) = [5s² × tan(54°)] / 4
		return (5 * Math.pow(super.getSide(), 2) * Math.tan(Math.toRadians(54))) / 4;
	}
}
