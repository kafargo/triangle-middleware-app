package org.msse640.triangle.controller;

import org.msse640.triangle.model.Quadrilateral;
import org.msse640.triangle.service.QuadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.msse640.triangle.dto.QuadResponse;

/**
 * REST Controller for evaluating quadrilaterals.
 * 
 * Endpoints:
 * - POST /type: evaluates a new quadrilateral and updates in-memory state
 * - PUT /test/default: updates the current default sides
 * - GET /test/default: returns the current default sides and shape type
 */

@RestController
@RequestMapping("/quadrilateral")
public class QuadController {

    // Inject the QuadService to manage in-memory side values
    @Autowired
    private QuadService quadService;

    /**
     * POST endpoint that takes side lengths directly from the user
     * via query parameters and returns the quadrilateral type.
     * 
     * Example: POST /quadrilateral/type?side1=5&side2=5&side3=5&side4=5
     */
    @PostMapping("/type")
    public QuadResponse getQuadType(
            @RequestParam double side1,
            @RequestParam double side2,
            @RequestParam double side3,
            @RequestParam double side4) 
        {

        // Store the values so GET will reflect them
        quadService.updateSides(side1, side2, side3, side4);

        // Still return the result based on the provided sides
        Quadrilateral q = new Quadrilateral(side1, side2, side3, side4);
        return new QuadResponse(side1, side2, side3, side4, q.getType());
    }


    /**
     * GET endpoint that returns the type of quadrilateral
     * using the current default side values stored in memory.
     *
     * This endpoint depends on the user having submitted side values via the POST /quadrilateral/type endpoint.
     * If no sides have been initialized yet, it returns a 400 Bad Request response with an instructional message.
     *
     * Uses ResponseEntity to:
     * - Return a structured JSON response (QuadResponse) if sides are available
     * - Return a 400 error with a message if sides have not been set
     *
     * Example:
     * GET /quadrilateral/test/default
     */
    
    @GetMapping("/test/default")
    public ResponseEntity<?> testDefault() {
        if (!quadService.isInitialized()) {
            return ResponseEntity.badRequest().body("No sides have been set. Please use POST /quadrilateral/type first.");
        }

        double s1 = quadService.getSide1();
        double s2 = quadService.getSide2();
        double s3 = quadService.getSide3();
        double s4 = quadService.getSide4();

        Quadrilateral q = new Quadrilateral(s1, s2, s3, s4);
        return ResponseEntity.ok(new QuadResponse(s1, s2, s3, s4, q.getType()));
    }
    



    /**
     * PUT endpoint to update the default side values.
     * 
     * These values are stored in memory and will be used by the GET endpoint to evaluate the quadrilateral.
     * This endpoint will return an error response if the default sides have not been initialized yet.
     * 
     * Initialization happens only when the user first calls POST /quadrilateral/type.
     * Until then, this endpoint is disabled to ensure the app starts in a blank state.
     *
     * Returns:
     * - 200 OK with a confirmation message if the sides were updated
     * - 400 Bad Request if no sides have been set yet (user hasn't POSTed)
     *
     * Example:
     * PUT /quadrilateral/test/default?side1=6&side2=4&side3=6&side4=4
     */

    @PutMapping("/test/default")
    public ResponseEntity<?> updateDefaults(
            @RequestParam double side1,
            @RequestParam double side2,
            @RequestParam double side3,
            @RequestParam double side4
    ) {
        if (!quadService.isInitialized()) {
            return ResponseEntity.badRequest().body("Cannot update sides. Please use POST /quadrilateral/type first.");
        }
    
        quadService.updateSides(side1, side2, side3, side4);
        return ResponseEntity.ok(String.format("Defaults updated to: [%s, %s, %s, %s]", side1, side2, side3, side4));
    }     


    /**
     * DELETE endpoint to reset the default side values to 0.
     * 
     * This will also mark the service as uninitialized, so the user must call POST /quadrilateral/type again
     * to set new sides before using the GET endpoint.
     *
     * Returns:
     * - 200 OK with a confirmation message if the sides were reset
     *
     * Example:
     * DELETE /quadrilateral/test/default
     */
    
    @DeleteMapping("/test/default")
    public ResponseEntity<String> deleteDefaults() {
        quadService.reset();
        return ResponseEntity.ok("Stored side values have been cleared. Please POST new values.");
    }
}