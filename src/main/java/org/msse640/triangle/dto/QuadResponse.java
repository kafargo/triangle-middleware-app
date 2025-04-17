package org.msse640.triangle.dto;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * QuadResponse is a simple Data Transfer Object (DTO) used in API responses.
 * It contains the 4 sides input and the resulting quadrilateral type.
 * 
 * This improves API clarity by structuring the output instead of returning plain strings.
 * 
 * Shows the customization in Swagger with the import
 */

public class QuadResponse {

    @Schema(description = "Length of side 1", example = "1.0 (Default)")
    private final double sideA;

    @Schema(description = "Length of side 2", example = "1.0 (Default)")
    private final double sideB;

    @Schema(description = "Length of side 3", example = "1.0 (Default)")
    private final double sideC;

    @Schema(description = "Length of side 4", example = "1.0 (Default)")
    private final double sideD;

    @Schema(description = "Resulting quadrilateral type", example = "Type of Quadrilateral: Square")
    private final String type;

    public QuadResponse(double sideA, double sideB, double sideC, double sideD, String type) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
        this.sideD = sideD;
        this.type = type;
    }

    public double getSideA() {
        return sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public double getSideC() {
        return sideC;
    }

    public double getSideD() {
        return sideD;
    }

    public String getType() {
        return type;
    }
}
