package shapes;

public class Pyramid extends Shape
{
	private double side;

	public Pyramid(double height) {
		super(height);
		this.side = side;
		
	}

	@Override
	public double calcVolume() {
		return (1/3) * side * side * super.getHeight();
	}

	@Override
	public double calcBaseArea() {
		return side * side;
	}

}
