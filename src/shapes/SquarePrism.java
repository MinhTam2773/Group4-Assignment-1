package shapes;

public class SquarePrism extends Prism
{

	public SquarePrism(double height, double side) {
		super(height, side);
	}

	@Override
	public double calcVolume() {
		return super.getSide() * super.getSide() * super.getHeight();
	}

	@Override
	public double calcBaseArea() {
		return super.getSide() * super.getSide();
	}
	
}
