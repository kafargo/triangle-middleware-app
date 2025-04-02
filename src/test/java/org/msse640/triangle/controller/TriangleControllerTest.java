package org.msse640.triangle.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
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
                .with(jwt()) // Mock a valid JWT token
                .param("side1", "3")
                .param("side2", "3")
                .param("side3", "3"))
                .andExpect(status().isOk())
                .andExpect(content().string("Type of Triangle: Equilateral Triangle"));
    }

    @Test
    public void testGetTriangleTypeEquilateralDecimal() throws Exception {
        mockMvc.perform(post("/triangle/type")
                .with(jwt()) // Mock a valid JWT token
                .param("side1", "6.2")
                .param("side2", "6.2")
                .param("side3", "6.2"))
                .andExpect(status().isOk())
                .andExpect(content().string("Type of Triangle: Equilateral Triangle"));
    }

    @Test
    public void testGetTriangleTypeIsosceles() throws Exception {
        mockMvc.perform(post("/triangle/type")
                .with(jwt()) // Mock a valid JWT token
                .param("side1", "3")
                .param("side2", "3")
                .param("side3", "4"))
                .andExpect(status().isOk())
                .andExpect(content().string("Type of Triangle: Isosceles Triangle"));
    }

    @Test
    public void testGetTriangleTypeScalene() throws Exception {
        mockMvc.perform(post("/triangle/type")
                .with(jwt()) // Mock a valid JWT token
                .param("side1", "3")
                .param("side2", "4")
                .param("side3", "5"))
                .andExpect(status().isOk())
                .andExpect(content().string("Type of Triangle: Scalene Triangle"));
    }

    @Test
    public void testGetTriangleTypeInvalid() throws Exception {
        mockMvc.perform(post("/triangle/type")
                .with(jwt()) // Mock a valid JWT token
                .param("side1", "0")
                .param("side2", "4")
                .param("side3", "5"))
                .andExpect(status().isOk())
                .andExpect(content().string("Invalid user input triangle sides. Please enter valid sides. x < 0"));
    }

    @Test
    public void testGetTriangleTypeInvalidInput() throws Exception {
        mockMvc.perform(post("/triangle/type")
                .with(jwt()) // Mock a valid JWT token
                .param("side1", "0")
                .param("side2", "0")
                .param("side3", "0"))
                .andExpect(status().isOk())
                .andExpect(content().string("Invalid user input triangle sides. Please enter valid sides. x < 0"));
    }

    @Test
    public void testGetTriangleTypeInvalidsideInput() throws Exception {
        mockMvc.perform(post("/triangle/type")
                .with(jwt()) // Mock a valid JWT token
                .param("side1", "5")
                .param("side2", "6")
                .param("side3", " "))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetTriangleTypeDecimalInput() throws Exception {
        mockMvc.perform(post("/triangle/type")
                .with(jwt()) // Mock a valid JWT token
                .param("side1", "3.5")
                .param("side2", "4.5")
                .param("side3", "5.5"))
                .andExpect(status().isOk())
                .andExpect(content().string("Type of Triangle: Scalene Triangle"));
    }

    @Test
    public void testGetTriangleTypeInvalidnegativeInput() throws Exception {
        mockMvc.perform(post("/triangle/type")
                .with(jwt()) // Mock a valid JWT token
                .param("side1", "-5")
                .param("side2", "2")
                .param("side3", "2"))
                .andExpect(status().isOk())
                .andExpect(content().string("Invalid user input triangle sides. Please enter valid sides. x < 0"));
    }

    @Test
    public void testGetTriangleTypeWithInvalidStringInput() throws Exception {
        mockMvc.perform(post("/triangle/type")
                .with(jwt()) // Mock a valid JWT token
                .param("side1", "abc") // Invalid string input
                .param("side2", "4")
                .param("side3", "5"))
                .andExpect(status().isBadRequest());
    }
}