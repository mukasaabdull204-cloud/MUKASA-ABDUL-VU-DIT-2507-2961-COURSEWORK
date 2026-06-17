/**
 * Custom unchecked exception thrown when an invalid dimension is provided
 * to any Shape subclass constructor or when resize() receives a non-positive factor.
 *
 * It is UNCHECKED (extends RuntimeException) because:
 *  - Invalid dimensions represent programming errors (passing bad data),
 *    not recoverable I/O or external conditions.
 *  - The Java convention is to use unchecked exceptions for violations of
 *    method pre-conditions (e.g., IllegalArgumentException pattern).
 *  - Callers are not forced to wrap every shape construction in try-catch,
 *    keeping client code cleaner, while still allowing optional catching
 *    where needed (as demonstrated in ShapeDemo).
 *
 * @author Mukasa
 */
public class InvalidShapeException extends RuntimeException {

    /**
     * Constructs an InvalidShapeException with a descriptive message.
     *
     * @param message explanation of why the shape/dimension is invalid
     */
    public InvalidShapeException(String message) {
        super(message);
    }
}