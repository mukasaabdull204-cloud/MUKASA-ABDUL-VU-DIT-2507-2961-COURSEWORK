/**
 * ShapeDemo – driver class for the geometric shapes hierarchy.
 *
 * Demonstrates:
 *  1. Creating valid Circle, Rectangle, Triangle objects
 *  2. Catching InvalidShapeException for an invalid triangle
 *  3. printAreas() – dynamic binding / polymorphism
 *  4. largest()    – finds shape with greatest area
 *  5. resize()     – scaling a shape
 *  6. Attempting a bad resize factor (caught)
 *
 * Dynamic binding explanation (see comment inside printAreas):
 *   When shapes[i].getArea() is called through the Shape superclass reference,
 *   the JVM dispatches to the ACTUAL subclass implementation at runtime.
 *   This is dynamic binding: the method resolved depends on the object's
 *   real type, not the declared type of the variable.
 *
 * @author Mukasa
 */
public class ShapeDemo {

    // ------------------------------------------------------------------ //
    //  printAreas – demonstrates dynamic binding
    // ------------------------------------------------------------------ //

    /**
     * Prints the area of each shape using a superclass (Shape) reference.
     * Dynamic binding ensures the correct getArea() override is called
     * for each actual object type at runtime.
     */
    public static void printAreas(Shape[] shapes) {
        System.out.println("\n--- printAreas() : dynamic binding demo ---");
        for (Shape s : shapes) {
            // s is declared as Shape (superclass reference), but the
            // JVM calls Circle.getArea(), Rectangle.getArea(), or
            // Triangle.getArea() depending on the REAL type of s.
            System.out.println(s.getClass().getSimpleName()
                    + " area = " + String.format("%.2f", s.getArea()));
        }
    }

    // ------------------------------------------------------------------ //
    //  largest – returns shape with greatest area
    // ------------------------------------------------------------------ //

    /**
     * Returns the shape with the largest area by comparing getArea() values.
     */
    public static Shape largest(Shape[] shapes) {
        if (shapes == null || shapes.length == 0) return null;
        Shape biggest = shapes[0];
        for (int i = 1; i < shapes.length; i++) {
            if (shapes[i].getArea() > biggest.getArea()) {
                biggest = shapes[i];
            }
        }
        return biggest;
    }

    // ------------------------------------------------------------------ //
    //  main
    // ------------------------------------------------------------------ //

    public static void main(String[] args) {

        // ----  1. Create valid shapes  ---- //
        Shape circle    = new Circle(5.0,            "red",   true);
        Shape rectangle = new Rectangle(4.0, 6.0,   "blue",  false);
        Shape triangle  = new Triangle(3.0, 4.0, 5.0, "green", true);

        Shape[] shapes = { circle, rectangle, triangle };

        // ----  2. Print all shapes  ---- //
        System.out.println("===== ALL SHAPES =====");
        for (Shape s : shapes) System.out.println(s);

        // ----  3. printAreas – dynamic binding  ---- //
        printAreas(shapes);

        // ----  4. Largest shape  ---- //
        Shape big = largest(shapes);
        System.out.println("\n--- Largest shape ---");
        System.out.println(big);

        // ----  5. Resize demo  ---- //
        System.out.println("\n--- Resize Circle by factor 2 ---");
        circle.resize(2);
        System.out.println("After resize: " + circle);

        // ----  6. Catch InvalidShapeException – invalid triangle  ---- //
        System.out.println("\n--- Attempting to create an INVALID triangle (1,2,10) ---");
        try {
            Triangle bad = new Triangle(1, 2, 10);   // violates triangle inequality
            System.out.println("Created: " + bad);   // should NOT reach here
        } catch (InvalidShapeException e) {
            System.out.println("Caught InvalidShapeException: " + e.getMessage());
        }

        // ----  7. Catch invalid resize factor  ---- //
        System.out.println("\n--- Attempting resize with factor -1 ---");
        try {
            rectangle.resize(-1);
        } catch (InvalidShapeException e) {
            System.out.println("Caught InvalidShapeException: " + e.getMessage());
        }

        // ----  8. Final state after all operations  ---- //
        System.out.println("\n===== FINAL STATE OF SHAPES =====");
        for (Shape s : shapes) System.out.println(s);
    }
}