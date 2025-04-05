package org.msse640.triangle.service;

import org.springframework.stereotype.Service;

/**
 * QuadService is an in-memory state manager for quadrilateral side values.
 * It stores and updates the default side lengths used by GET and POST endpoints.
 * 
 * This class does not persist data — values are reset on app restart.
 */

@Service
public class QuadService {
    private double side1;
    private double side2;
    private double side3;
    private double side4;

    // Track whether the user has posted sides yet
    private boolean initialized = false;

    public boolean isInitialized() {
        return initialized;
    }

    // Getters used by GET endpoint to fetch the current default side values
    public double getSide1() { return side1; }
    public double getSide2() { return side2; }
    public double getSide3() { return side3; }
    public double getSide4() { return side4; }


    // Used by PUT and POST endpoints to update the stored side values
    public void updateSides(double s1, double s2, double s3, double s4) {
        this.side1 = s1;
        this.side2 = s2;
        this.side3 = s3;
        this.side4 = s4;
        this.initialized = true; //IMPORTANT to initialize user input or not
    }

    // Used by DELETE endpoint to reset the stored side values to 0
    // and mark the service as uninitialized
    public void reset() {
        this.side1 = 0;
        this.side2 = 0;
        this.side3 = 0;
        this.side4 = 0;
        this.initialized = false;
    }
}

