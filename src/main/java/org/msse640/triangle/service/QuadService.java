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
    private double sideA;
    private double sideB;
    private double sideC;
    private double sideD;

    // Track whether the user has posted sides yet
    private boolean initialized = false;

    public boolean isInitialized() {
        return initialized;
    }

    // Getters used by GET endpoint to fetch the current default side values
    public double getSideA() { return sideA; }
    public double getSideB() { return sideB; }
    public double getSideC() { return sideC; }
    public double getSideD() { return sideD; }


    // Used by PUT and POST endpoints to update the stored side values
    public void updateSides(double sideA, double sideB, double sideC, double sideD) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
        this.sideD = sideD;
        this.initialized = true; //IMPORTANT to initialize user input or not
    }

    // method to uninitialize
    public void reset() {
        this.initialized = false;
    }

}

