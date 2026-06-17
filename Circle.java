/**
 * Concrete subclass representing a Circle.
 *
 * @author Mukasa
 */
public class Circle extends Shape {

    private double radius;

    // ------------------------------------------------------------------ //
    //  Constructors
    // ------------------------------------------------------------------ //

    public Circle(double radius) {
        super();
        validateRadius(radius);
        this.radius = radius;
    }

    public Circle(double radius, String color, boolean filled) {
        super(color, filled);
        validateRadius(radius);
        this.radius = radius;
    }

    // ------------------------------------------------------------------ //
    //  Validation helper
    // ------------------------------------------------------------------ //

    private void validateRadius(double r) {
        if (r <= 0) {
            throw new InvalidShapeException(
                    "Circle radius must be positive, got: " + r);
        }
    }

    // ------------------------------------------------------------------ //
    //  Abstract method implementations
    // ------------------------------------------------------------------ //

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    /**
     * Scales the radius by {@code factor}.
     * A circle with radius r resized by factor f becomes radius r*f.
     */
    @Override
    public void resize(double factor) {
        if (factor <= 0) {
            throw new InvalidShapeException(
                    "Resize factor must be positive, got: " + factor);
        }
        radius *= factor;
    }

    // ------------------------------------------------------------------ //
    //  Getter / setter
    // ------------------------------------------------------------------ //

    public double getRadius()           { return radius; }
    public void   setRadius(double r)   { validateRadius(r); this.radius = r; }

    @Override
    public String toString() {
        return "Circle[radius=" + radius + ", " + super.toString().replaceFirst(".*\\[", "") ;
    }
}