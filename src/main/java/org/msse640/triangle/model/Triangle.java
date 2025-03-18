package org.msse640.triangle.model;

public class Triangle {
    private double sideA;
    private double sideB;
    private double sideC;

    public Triangle(double sideA, double sideB, double sideC) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    public String getType() {
        if (sideA <= 0 || sideB <= 0 || sideC <= 0) {
            return "Invalid triangle sides";
        }
        if (sideA == sideB && sideB == sideC) {
            return "Equilateral";
        } else if (sideA == sideB || sideB == sideC || sideA == sideC) {
            return "Isosceles";
        } else {
            return "Scalene";
        }
    }

}