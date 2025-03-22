package org.msse640.triangle.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TriangleController.class)
public class TriangleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetTriangleTypeEquilateral() throws Exception {
        mockMvc.perform(post("/triangle/type")
                .param("side1", "3")
                .param("side2", "3")
                .param("side3", "3"))
                .andExpect(status().isOk())
                .andExpect(content().string("Equilateral"));
    }

    @Test
    public void testGetTriangleTypeIsosceles() throws Exception {
        mockMvc.perform(post("/triangle/type")
                .param("side1", "3")
                .param("side2", "3")
                .param("side3", "4"))
                .andExpect(status().isOk())
                .andExpect(content().string("Isosceles"));
    }

    @Test
    public void testGetTriangleTypeScalene() throws Exception {
        mockMvc.perform(post("/triangle/type")
                .param("side1", "3")
                .param("side2", "4")
                .param("side3", "5"))
                .andExpect(status().isOk())
                .andExpect(content().string("Scalene"));
    }

    @Test
    public void testGetTriangleTypeInvalid() throws Exception {
        mockMvc.perform(post("/triangle/type")
                .param("side1", "0")
                .param("side2", "4")
                .param("side3", "5"))
                .andExpect(status().isOk())
                .andExpect(content().string("Invalid triangle sides"));
    }

    @Test
    public void testGetTriangleTypeWithInvalidStringInput() throws Exception {
        mockMvc.perform(post("/triangle/type")
                .param("side1", "abc") // Invalid string input
                .param("side2", "4")
                .param("side3", "5"))
                .andExpect(status().isBadRequest());
    }
}