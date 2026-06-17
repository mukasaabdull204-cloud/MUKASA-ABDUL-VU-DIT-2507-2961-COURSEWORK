/**
 * Abstract base class for all geometric shapes.
 *
 * Declared abstract because:
 *  - A "shape" in isolation has no concrete geometry; you cannot calculate
 *    the area or perimeter of a generic shape without knowing what kind it is.
 *  - Attempting new Shape() directly causes a compile-time error:
 *    "Shape is abstract; cannot be instantiated."
 *
 * @author Mukasa
 */
public abstract class Shape {

    // ---- protected fields: visible to subclasses ---- //
    protected String  color;
    protected boolean filled;

    // ------------------------------------------------------------------ //
    //  Constructors
    // ------------------------------------------------------------------ //

    /** Default constructor – white, unfilled. */
    public Shape() {
        this.color  = "white";
        this.filled = false;
    }

    /** Parameterised constructor. */
    public Shape(String color, boolean filled) {
        this.color  = color;
        this.filled = filled;
    }

    // ------------------------------------------------------------------ //
    //  Abstract methods – every concrete subclass MUST override these
    // ------------------------------------------------------------------ //

    /** Returns the area of the shape. */
    public abstract double getArea();

    /** Returns the perimeter of the shape. */
    public abstract double getPerimeter();

    /**
     * Scales all linear dimensions by {@code factor}.
     *
     * @param factor  positive multiplier; must be > 0
     * @throws InvalidShapeException if factor is non-positive
     */
    public abstract void resize(double factor);

    // ------------------------------------------------------------------ //
    //  Getters / setters
    // ------------------------------------------------------------------ //

    public String  getColor()  { return color; }
    public boolean isFilled()  { return filled; }
    public void setColor(String color)    { this.color  = color; }
    public void setFilled(boolean filled) { this.filled = filled; }

    // ------------------------------------------------------------------ //
    //  Concrete toString shared by all shapes
    // ------------------------------------------------------------------ //

    @Override
    public String toString() {
        return getClass().getSimpleName()
                + "[color=" + color
                + ", filled=" + filled
                + ", area="      + String.format("%.2f", getArea())
                + ", perimeter=" + String.format("%.2f", getPerimeter())
                + "]";
    }
}