package shapes;

public abstract class Shape implements Comparable<Shape> {
	private double height;
	
	public abstract double calcVolume();
	public abstract double calcBaseArea();
	
	public Shape(double height) {
		this.height = height;
	}

	public double getHeight() {
		return height;
	}
	
	@Override
	public int compareTo(Shape that) {
		if (this.height > that.height) return 1;
		else if (this.height < that.height) return -1;
		else return 0;
	}
}
