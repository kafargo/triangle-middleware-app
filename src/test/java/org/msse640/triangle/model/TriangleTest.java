package org.msse640.triangle.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TriangleTest {

    @Test
    public void testEquilateralTriangle() {
        Triangle triangle = new Triangle(3, 3, 3);
        assertEquals("Type of Triangle: Equilateral Triangle", triangle.getType());
    }

    @Test
    public void testIsoscelesTriangle() {
        Triangle triangle = new Triangle(3, 3, 4);
        assertEquals("Type of Triangle: Isosceles Triangle", triangle.getType());
    }

    @Test
    public void testScaleneTriangle() {
        Triangle triangle = new Triangle(3, 4, 5);
        assertEquals("Type of Triangle: Scalene Triangle", triangle.getType());
    }

    @Test
    public void testInvalidTriangle() {
        Triangle triangle = new Triangle(0, 4, 5);
        assertEquals("Invalid user input triangle sides. Please enter valid sides. x < 0", triangle.getType());
    }

    @Test
    public void testInvalidTriangleWithStrings() {
        try {
            @SuppressWarnings("unused")
            Triangle triangle = new Triangle(Double.parseDouble("zyx"), 4, 5);
            fail("Expected NumberFormatException to be thrown");
        } catch (NumberFormatException e) {
            assertTrue(true);
        }
    }
}