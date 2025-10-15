package shapes;

/**
 * The {@code Pyramid} class represents a square-based pyramid, a 3D shape
 * with a square base and four triangular faces converging to a single apex.
 * <p>
 * It extends {@link Shape} and provides concrete methods for calculating
 * volume and base area.
 * <p>
 * Formulas used:
 * <ul>
 *   <li>Base Area = s²</li>
 *   <li>Volume = (1/3) × Base Area × Height</li>
 * </ul>
 */
public class Pyramid extends Shape {
    private double side;

    /**
     * Constructs a {@code Pyramid} with a given height and base side length.
     * 
     * @param height the pyramid’s height
     * @param side the length of one side of the square base
     */
    public Pyramid(double height, double side) {
        super(height);
        this.side = side;
    }

    /** 
     * Calculates the volume of the pyramid.
     * @return the volume (in cubic units)
     */
    @Override
    public double calcVolume() {
        return (1.0 / 3) * side * side * super.getHeight();
    }

    /** 
     * Calculates the base area of the pyramid.
     * @return the base area (in square units)
     */
    @Override
    public double calcBaseArea() {
        return side * side;
    }
}
