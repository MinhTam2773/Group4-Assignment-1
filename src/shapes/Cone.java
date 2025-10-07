package shapes;

public class Cone extends Shape 
{
	private double radius;

	public Cone(double height, double radius) {
		super(height);
		this.radius = radius;
	}

	@Override
	public double calcVolume() {
		return (Math.PI * this.radius * this.radius * super.getHeight())/3;
	}

	@Override
	public double calcBaseArea() {
		return Math.PI * this.radius * this.radius;
	}

}