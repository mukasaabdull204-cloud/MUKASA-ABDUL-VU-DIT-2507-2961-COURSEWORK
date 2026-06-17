/**
 * Concrete subclass representing a Triangle (defined by its three sides).
 * Uses Heron's formula to compute area.
 *
 * @author Mukasa
 */
public class Triangle extends Shape {

    private double sideA;
    private double sideB;
    private double sideC;

    // ------------------------------------------------------------------ //
    //  Constructors
    // ------------------------------------------------------------------ //

    public Triangle(double sideA, double sideB, double sideC) {
        super();
        validateSides(sideA, sideB, sideC);
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    public Triangle(double sideA, double sideB, double sideC,
                    String color, boolean filled) {
        super(color, filled);
        validateSides(sideA, sideB, sideC);
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    // ------------------------------------------------------------------ //
    //  Validation helper
    // ------------------------------------------------------------------ //

    private void validateSides(double a, double b, double c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new InvalidShapeException(
                    "All triangle sides must be positive. Got: "
                    + a + ", " + b + ", " + c);
        }
        // Triangle inequality: sum of any two sides > third side
        if ((a + b <= c) || (a + c <= b) || (b + c <= a)) {
            throw new InvalidShapeException(
                    "Triangle inequality violated: sides " + a + ", " + b + ", " + c
                    + " cannot form a valid triangle.");
        }
    }

    // ------------------------------------------------------------------ //
    //  Abstract method implementations
    // ------------------------------------------------------------------ //

    @Override
    public double getArea() {
        // Heron's formula: s = semi-perimeter
        double s = (sideA + sideB + sideC) / 2.0;
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
    }

    @Override
    public double getPerimeter() {
        return sideA + sideB + sideC;
    }

    /**
     * Scales all three sides by {@code factor}.
     */
    @Override
    public void resize(double factor) {
        if (factor <= 0) {
            throw new InvalidShapeException(
                    "Resize factor must be positive, got: " + factor);
        }
        sideA *= factor;
        sideB *= factor;
        sideC *= factor;
    }

    // ------------------------------------------------------------------ //
    //  Getters / setters
    // ------------------------------------------------------------------ //

    public double getSideA() { return sideA; }
    public double getSideB() { return sideB; }
    public double getSideC() { return sideC; }

    public void setSides(double a, double b, double c) {
        validateSides(a, b, c);
        sideA = a; sideB = b; sideC = c;
    }

    @Override
    public String toString() {
        return "Triangle[sides=" + sideA + "," + sideB + "," + sideC
                + ", color=" + color + ", filled=" + filled
                + ", area="      + String.format("%.2f", getArea())
                + ", perimeter=" + String.format("%.2f", getPerimeter()) + "]";
    }
}
