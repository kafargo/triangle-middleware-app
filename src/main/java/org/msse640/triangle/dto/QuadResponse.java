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
    public double side1;

    @Schema(description = "Length of side 2", example = "1.0 (Default)")
    public double side2;

    @Schema(description = "Length of side 3", example = "1.0 (Default)")
    public double side3;

    @Schema(description = "Length of side 4", example = "1.0 (Default)")
    public double side4;

    @Schema(description = "Resulting quadrilateral type", example = "Type of Quadrilateral: Square")
    public String type;

    public QuadResponse(double side1, double side2, double side3, double side4, String type) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        this.side4 = side4;
        this.type = type;
    }
}
