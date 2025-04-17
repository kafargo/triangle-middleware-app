package org.msse640.triangle.controller;

import org.msse640.triangle.model.Quadrilateral;
import org.msse640.triangle.service.QuadService;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

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
@RequestMapping("/quad")
public class QuadController {

    // Inject the QuadService to manage in-memory side values
    @Autowired
    private QuadService quadService;

    public QuadController(QuadService quadService) {
        this.quadService = quadService;
    }

    /**
     * POST endpoint that takes side lengths directly from the user
     * via query parameters and returns the quadrilateral type.
     * 
     * Example: POST /quadrilateral/type?sideA=5&sideB=5&sideC=5&sideD=5
     */

    @PostMapping("/type")
    public ResponseEntity<?> postQuadrilateral(
            @RequestParam double sideA,
            @RequestParam double sideB,
            @RequestParam double sideC,
            @RequestParam double sideD
    ) {
        // 1. Delegate all validation + typing to the model
        Quadrilateral quad = new Quadrilateral(sideA, sideB, sideC, sideD);
        String type = quad.getType();
    
        // 2. If the model says “Invalid input…”, reset and return JSON error
        if (type.startsWith("Invalid input")) {
            // rule #1: invalid POST should uninitialize
            quadService.reset();
            return ResponseEntity
                .badRequest()
                .body(Map.of("error", type));
        }
    
        // 3. Otherwise persist and return the structured response
        quadService.updateSides(sideA, sideB, sideC, sideD);
        QuadResponse response = new QuadResponse(sideA, sideB, sideC, sideD, type);
        return ResponseEntity.ok(response);
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
    
    @GetMapping("/type")
    public ResponseEntity<?> getQuadrilateral() {
        if (!quadService.isInitialized()) {
        // rule #2: no GET until POST
            return ResponseEntity
                .badRequest()
                .body(Collections.singletonMap("error", "Please POST sides first."));
        }

        double a = quadService.getSideA();
        double b = quadService.getSideB();
        double c = quadService.getSideC();
        double d = quadService.getSideD();
        String type = new Quadrilateral(a, b, c, d).getType();

        QuadResponse response = new QuadResponse(a, b, c, d, type);
        return ResponseEntity.ok(response);
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
     * PUT /quadrilateral/test/default?sideA=6&sideB=4&sideC=6&sideD=4
     */

    @PutMapping("/type")
    public ResponseEntity<?> putQuadrilateral(
            @RequestParam double sideA,
            @RequestParam double sideB,
            @RequestParam double sideC,
            @RequestParam double sideD
    ) {
        if (!quadService.isInitialized()) {
            //rule #3a: PUT → same guard as GET
            return ResponseEntity
            .badRequest()
            .body(Collections.singletonMap("error", "Please POST sides first."));
        }
    
        quadService.updateSides(sideA, sideB, sideC, sideD);
        String type = new Quadrilateral(sideA, sideB, sideC, sideD).getType();
        QuadResponse response = new QuadResponse(sideA, sideB, sideC, sideD, type);
        return ResponseEntity.ok(response);
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
    
    @DeleteMapping("/type")
    public ResponseEntity<?> deleteQuadrilateral() {
        // rule #3b: DELETE → reset and return confirmation
        //same guard as GET and PUT
        if (!quadService.isInitialized()) {
            return ResponseEntity
                .badRequest()
                .body(Collections.singletonMap("error", "Please POST sides first."));
        }
        quadService.reset();
        return ResponseEntity.ok("Quadrilateral data has been reset.");
    }
}