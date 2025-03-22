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

    //Function to check if the input sides are valid
    public static boolean inputCheck(double sideA, double sideB, double sideC) {
        if ( sideA <= 0 || sideB <= 0 || sideC <= 0 ) {
            return false;
        } else {
            return true;
        }
    }

    // Function to check if the given sides form a valid triangle
    public static boolean isValidTriangle(double sideA, double sideB, double sideC) {
        // Check the triangle inequality theorem to determine validity
        return (sideA + sideB > sideC && sideB + sideC > sideA && sideC + sideA > sideB);
    }

    // Function to determine the type of triangle
    public String getType() {

        // Check if the input sides are valid
        if (!inputCheck(sideA, sideB, sideC)) {
            return "Invalid user input triangle sides. Please enter valid sides. x < 0";
        }
        
        //Boundary check to see if inputs make a valid triangle or not
        if (isValidTriangle(sideA, sideB, sideC)) {
            
            //Check for Equilateral Triangle
            if (sideA == sideB && sideB == sideC) {
                return "Type of Triangle: Equilateral Triangle";
            }

            //Check for Isosceles Triangle
            else if (sideA == sideB || sideB == sideC || sideC == sideA) {
                return "Type of Triangle: Isosceles Triangle";
            }
            
            //Check for Scalene Triangle
            // (sideA!= sideB || sideB!= sideC || sideC!= sideA) will be true for Scalene Triangle
            else { 
                return "Type of Triangle: Scalene Triangle";
            }
            
        } else {
            return "Not a Triangle. Invalid sides based on the Triangle Inequality Theorem.";
        }
        
        //Previous getType function
        //     if (sideA <= 0 || sideB <= 0 || sideC <= 0) {
        //         return "Invalid triangle sides";
        //     }
        //     if (sideA == sideB && sideB == sideC) {
        //         return "Equilateral";
        //     } else if (sideA == sideB || sideB == sideC || sideA == sideC) {
        //         return "Isosceles";
        //     } else {
        //         return "Scalene";
        //     }
    }
}