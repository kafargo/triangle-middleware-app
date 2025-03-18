package org.msse640.triangle.controller;

import org.msse640.triangle.model.Triangle;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/triangle")
public class TriangleController {

    @PostMapping("/type")
    public String getTriangleType(@RequestParam double side1, @RequestParam double side2, @RequestParam double side3) {
        Triangle triangle = new Triangle(side1, side2, side3);
        return triangle.getType();
    }
}