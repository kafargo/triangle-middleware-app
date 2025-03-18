package org.msse640.triangle.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TriangleTest {

    @Test
    public void testEquilateralTriangle() {
        Triangle triangle = new Triangle(3, 3, 3);
        assertEquals("Equilateral", triangle.getType());
    }

    @Test
    public void testIsoscelesTriangle() {
        Triangle triangle = new Triangle(3, 3, 4);
        assertEquals("Isosceles", triangle.getType());
    }

    @Test
    public void testScaleneTriangle() {
        Triangle triangle = new Triangle(3, 4, 5);
        assertEquals("Scalene", triangle.getType());
    }

    @Test
    public void testInvalidTriangle() {
        Triangle triangle = new Triangle(0, 4, 5);
        assertEquals("Invalid triangle sides", triangle.getType());
    }

    @Test
    public void testInvalidTriangleWithStrings() {
        try {
            Triangle triangle = new Triangle(Double.parseDouble("zyx"), 4, 5);
            fail("Expected NumberFormatException to be thrown");
        } catch (NumberFormatException e) {
            assertTrue(true);
        }
    }
}