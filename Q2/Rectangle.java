/**
 * Concrete subclass representing a Rectangle.
 *
 * @author Mukasa
 */
public class Rectangle extends Shape {

    private double width;
    private double height;

    // ------------------------------------------------------------------ //
    //  Constructors
    // ------------------------------------------------------------------ //

    public Rectangle(double width, double height) {
        super();
        validateDimensions(width, height);
        this.width  = width;
        this.height = height;
    }

    public Rectangle(double width, double height, String color, boolean filled) {
        super(color, filled);
        validateDimensions(width, height);
        this.width  = width;
        this.height = height;
    }

    // ------------------------------------------------------------------ //
    //  Validation helper
    // ------------------------------------------------------------------ //

    private void validateDimensions(double w, double h) {
        if (w <= 0 || h <= 0) {
            throw new InvalidShapeException(
                    "Rectangle dimensions must be positive. Got width=" + w + ", height=" + h);
        }
    }

    // ------------------------------------------------------------------ //
    //  Abstract method implementations
    // ------------------------------------------------------------------ //

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    /**
     * Scales both width and height by {@code factor}.
     * Doubling the factor doubles each linear dimension (area quadruples).
     */
    @Override
    public void resize(double factor) {
        if (factor <= 0) {
            throw new InvalidShapeException(
                    "Resize factor must be positive, got: " + factor);
        }
        width  *= factor;
        height *= factor;
    }

    // ------------------------------------------------------------------ //
    //  Getters / setters
    // ------------------------------------------------------------------ //

    public double getWidth()             { return width; }
    public double getHeight()            { return height; }
    public void   setWidth(double w)     { validateDimensions(w, height); this.width  = w; }
    public void   setHeight(double h)    { validateDimensions(width, h);  this.height = h; }

    @Override
    public String toString() {
        return "Rectangle[width=" + width + ", height=" + height
                + ", color=" + color + ", filled=" + filled
                + ", area="      + String.format("%.2f", getArea())
                + ", perimeter=" + String.format("%.2f", getPerimeter()) + "]";
    }
}
