package org.msse640.triangle.model;

/**
 * Quadrilateral represents a four‑sided shape defined by its side lengths.
 *
 * It provides:
 *  - input validation (all sides > 0 and must form a valid quadrilateral)
 *  - type determination (square, rectangle, rhombus placeholder, unknown)
 *
 * A quadrilateral is valid if and only if the sum of any three sides
 * is strictly greater than the remaining side.
 */
public class Quadrilateral {
    private final double sideA;
    private final double sideB;
    private final double sideC;
    private final double sideD;

    public Quadrilateral(double sideA, double sideB, double sideC, double sideD) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
        this.sideD = sideD;
    }

    public static boolean hasNullOrInvalid(Object a, Object b, Object c, Object d) {
        try {
            if (a == null || b == null || c == null || d == null) return true;
            Double.parseDouble(a.toString());
            Double.parseDouble(b.toString());
            Double.parseDouble(c.toString());
            Double.parseDouble(d.toString());
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }
    

    /**
     * Validates that:
     * 1. a, b, c, d are all > 0.
     * 2. The sum of any three sides is greater than the fourth.
     *
     * @return null if valid, otherwise a descriptive error message using a/b/c/d notation
     */
    public static String validate(double sideA, double sideB, double sideC, double sideD) {
        if (sideA <= 0 || sideB <= 0 || sideC <= 0 || sideD <= 0) {
            return "all sides (a, b, c, d) must be > 0";
        }
        if ((sideA + sideB + sideC) <= sideD) return "sum of a + b + c must be > d";
        if ((sideA + sideB + sideD) <= sideC) return "sum of a + b + d must be > c";
        if ((sideA + sideC + sideD) <= sideB) return "sum of a + c + d must be > b";
        if ((sideB + sideC + sideD) <= sideA) return "sum of b + c + d must be > a";
        return null;
    }

    /**
     * All four sides equal.
     */
    public static boolean allSidesEqual(double sideA, double sideB, double sideC, double sideD) {
        return sideA == sideB && sideB == sideC && sideC == sideD;
    }

    /**
     * Opposite sides equal (a == c and b == d) and adjacent not equal.
     */
    public static boolean isRectangle(double sideA, double sideB, double sideC, double sideD) {
        return (sideA == sideC && sideB == sideD) && (sideA != sideB);
    }

    /**
     * All sides equal: potential rhombus (angles not considered).
     */
    public static boolean isRhombus(double sideA, double sideB, double sideC, double sideD) {
        return allSidesEqual(sideA, sideB, sideC, sideD);
    }

    // Reusable "is this a valid quadrilateral?" check
    public static boolean isValidQuadrilateral(double sideA, double sideB, double sideC, double sideD) {
        return validate(sideA, sideB, sideC, sideD) == null;
    }

    /**
     * A parallelogram has opposite sides equal (a == c, b == d)
     */
    public static boolean isParallelogram(double sideA, double sideB, double sideC, double sideD) {
        return (sideA == sideC) && (sideB == sideD);
    }

    /**
     * A kite has two distinct pairs of adjacent sides equal
     */
    public static boolean isKite(double sideA, double sideB, double sideC, double sideD) {
        return (sideA == sideB && sideC == sideD && sideA != sideC) ||
            (sideB == sideC && sideD == sideA && sideB != sideD);
    }


    /**
     * Determine type based on side lengths.
     * Priority:
     * 1. Invalid input (with reason)
     * 2. Square
     * 3. Rectangle
     * 4. Rhombus
     * 5. Unknown
     *
     * @return descriptive type or error
     */
    public String getType() {
        String err = validate(sideA, sideB, sideC, sideD);
        if (err != null) {
            return "Invalid Quadrilateral: " + err + ".";
        }
        if (allSidesEqual(sideA, sideB, sideC, sideD)) {
            return "Type of Quadrilateral: Square";
        }
        if (isRectangle(sideA, sideB, sideC, sideD)) {
            return "Type of Quadrilateral: Rectangle";
        }
        if (isRhombus(sideA, sideB, sideC, sideD)) {
            return "Type of Quadrilateral: Rhombus (assuming not a square due to angle)";
        }
        if (isParallelogram(sideA, sideB, sideC, sideD)) {
            return "Type of Quadrilateral: Parallelogram";
        }
        if (isKite(sideA, sideB, sideC, sideD)) {
            return "Type of Quadrilateral: Kite";
        }
        return "Valid Quadrilateral (Generic convex quadrilateral)";
    }
    
}