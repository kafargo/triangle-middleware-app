package org.msse640.triangle.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.msse640.triangle.service.QuadService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuadController.class)
@AutoConfigureMockMvc
public class QuadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuadService quadService;

    @Test
    void post_returns400ForInvalidStringInput() throws Exception {
        mockMvc.perform(post("/quad/type")
                .param("sideA", "abc")
                .param("sideB", "5")
                .param("sideC", "6")
                .param("sideD", "7"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void post_returns400ForZeroOrNegativeInput() throws Exception {
        mockMvc.perform(post("/quad/type")
                .param("sideA", "0")
                .param("sideB", "5")
                .param("sideC", "6")
                .param("sideD", "7"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Quadrilateral: violates quadrilateral side length rules."));
    }

    @Test
    void post_returns400ForInvalidQuadrilateralInequality() throws Exception {
        mockMvc.perform(post("/quad/type")
                .param("sideA", "1")
                .param("sideB", "2")
                .param("sideC", "3")
                .param("sideD", "10"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Quadrilateral: violates quadrilateral side length rules."));
    }

    @Test
    void post_returns200ForSquare() throws Exception {
        mockMvc.perform(post("/quad/type")
                .param("sideA", "4")
                .param("sideB", "4")
                .param("sideC", "4")
                .param("sideD", "4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("Type of Quadrilateral: Square"));
    }

    @Test
    void post_returns200ForRectangle() throws Exception {
        mockMvc.perform(post("/quad/type")
                .param("sideA", "6")
                .param("sideB", "4")
                .param("sideC", "6")
                .param("sideD", "4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("Type of Quadrilateral: Rectangle"));
    }

    @Test
    void post_returns200ForDecimalInput() throws Exception {
        mockMvc.perform(post("/quad/type")
                .param("sideA", "3.5")
                .param("sideB", "3.5")
                .param("sideC", "3.5")
                .param("sideD", "3.5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("Type of Quadrilateral: Square"));
    }

    @Test
    void post_returns200ForGenericConvexQuadrilateral() throws Exception {
        mockMvc.perform(post("/quad/type")
                .param("sideA", "2")
                .param("sideB", "3")
                .param("sideC", "4")
                .param("sideD", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("Valid Quadrilateral (Generic convex quadrilateral)"));
    }

    @Test
    void put_returns400WhenNotInitialized() throws Exception {
        Mockito.when(quadService.isInitialized()).thenReturn(false);

        mockMvc.perform(put("/quad/type")
                .param("sideA", "2")
                .param("sideB", "3")
                .param("sideC", "4")
                .param("sideD", "5"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Please POST sides first."));
    }

    @Test
    void put_returns400ForInvalidStringInput() throws Exception {
        Mockito.when(quadService.isInitialized()).thenReturn(true);

        mockMvc.perform(put("/quad/type")
                .param("sideA", "abc")
                .param("sideB", "5")
                .param("sideC", "6")
                .param("sideD", "7"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void put_returns200ForValidSquare() throws Exception {
        Mockito.when(quadService.isInitialized()).thenReturn(true);

        mockMvc.perform(put("/quad/type")
                .param("sideA", "5")
                .param("sideB", "5")
                .param("sideC", "5")
                .param("sideD", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("Type of Quadrilateral: Square"));
    }

    @Test
    void post_invalidQuadrilateral_uninitializesState() throws Exception {
        // Step 1: Send invalid POST
        mockMvc.perform(post("/quad/type")
                .param("sideA", "1")
                .param("sideB", "2")
                .param("sideC", "3")
                .param("sideD", "10"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());

        // Step 2: Try to GET the current quadrilateral
        mockMvc.perform(get("/quad/type"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Please POST sides first."));
    }

    @Test
    void put_invalidQuadrilateral_uninitializesState() throws Exception {
        // Arrange: Pretend the app is initialized (this is important!)
        Mockito.when(quadService.isInitialized()).thenReturn(true);
    
        // Step 1: Send invalid PUT (bad sides that fail validation)
        mockMvc.perform(put("/quad/type")
                .param("sideA", "1")
                .param("sideB", "2")
                .param("sideC", "3")
                .param("sideD", "10"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
    
        // Step 2: Try to GET the current quadrilateral
        Mockito.when(quadService.isInitialized()).thenReturn(false); // Because reset() would have been called
    
        mockMvc.perform(get("/quad/type"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Please POST sides first."));
    }
    
}
