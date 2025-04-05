package org.msse640.triangle.model;

public class Quadrilateral {
    private double sideA;
    private double sideB;
    private double sideC;
    private double sideD;

    public Quadrilateral(double sideA, double sideB, double sideC, double sideD) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
        this.sideD = sideD;
    }

    /**
     * Input validation: All sides must be greater than 0
     * 
     * Example (invalid): sideA = 0, sideB = 4, sideC = 4, sideD = 4
     */
    public static boolean inputCheck(double a, double b, double c, double d) {
        return a > 0 && b > 0 && c > 0 && d > 0;
    }

    /**
     * Square Check: All four sides must be equal
     * 
     * Example (square): sideA = 5, sideB = 5, sideC = 5, sideD = 5
     */
    public static boolean isSquare(double a, double b, double c, double d) {
        return a == b && b == c && c == d;
    }

    /**
     * Rectangle Check: Opposite sides must be equal (A == C and B == D),
     * and adjacent sides must be different (A != B) to exclude square
     * 
     * Example (rectangle): sideA = 6, sideB = 4, sideC = 6, sideD = 4
     */
    public static boolean isRectangle(double a, double b, double c, double d) {
        return a == c && b == d && a != b;
    }

    /**
     * Rhombus Check: All sides equal, but assume it's not a square if user distinguishes by angle
     * In real geometry, rhombus ≠ square when interior angles ≠ 90°
     * 
     * Example (rhombus by assumption): sideA = 7, sideB = 7, sideC = 7, sideD = 7
     * (In our logic, this will still classify as a square unless we add angle checks)
     */
    public static boolean isRhombus(double a, double b, double c, double d) {
        return isSquare(a, b, c, d); // Placeholder; distinguish only if you include angles
    }

    /**
     * Determine the type of quadrilateral
     * Priority:
     *  - Invalid input
     *  - Square
     *  - Rectangle
     *  - Rhombus
     *  - Unknown shape
     * 
     * Example Inputs:
     *  - Square:     (5, 5, 5, 5)
     *  - Rectangle:  (6, 4, 6, 4)
     *  - Invalid:    (0, 4, 4, 4)
     *  - Unknown:    (5, 6, 7, 8)
     */
    public String getType() {
        if (!inputCheck(sideA, sideB, sideC, sideD)) {
            return "Invalid input: all sides must be greater than 0.";
        }

        if (isSquare(sideA, sideB, sideC, sideD)) {
            return "Type of Quadrilateral: Square";
        } else if (isRectangle(sideA, sideB, sideC, sideD)) {
            return "Type of Quadrilateral: Rectangle";
        } else if (sideA == sideB && sideB == sideC && sideC == sideD) {
            return "Type of Quadrilateral: Rhombus (assuming not a square due to angle)";
        } else {
            return "Not a standard square/rectangle/rhombus based on side lengths.";
        }
    }
}


