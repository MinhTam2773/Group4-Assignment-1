package shapes;

public abstract class Shape {
	private double height;
	
	public abstract double calcVolume();
	public abstract double calcBaseArea();
	
	public Shape(double height) {
		this.height = height;
	}

	public double getHeight() {
		return height;
	}
	
	public int compareTo(Shape s){
		if ( this.getHeight() > s.getHeight() ) return 1;
		else if ( this.getHeight() < s.getHeight() ) return -1;
		else return 0;
	}
}
