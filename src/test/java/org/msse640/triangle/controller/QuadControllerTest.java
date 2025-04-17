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
                // .andExpect(jsonPath("$.error").value("All inputs must be numeric and non-null."));
    }

    @Test
    void post_returns400ForZeroOrNegativeInput() throws Exception {
        mockMvc.perform(post("/quad/type")
                .param("sideA", "0")
                .param("sideB", "5")
                .param("sideC", "6")
                .param("sideD", "7"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Quadrilateral: all sides (a, b, c, d) must be > 0."));
    }

    @Test
    void post_returns400ForInvalidQuadrilateralInequality() throws Exception {
        mockMvc.perform(post("/quad/type")
                .param("sideA", "1")
                .param("sideB", "2")
                .param("sideC", "3")
                .param("sideD", "10"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid Quadrilateral: sum of a + b + c must be > d."));
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
    void post_returns200ForUnknownQuadrilateral() throws Exception {
        mockMvc.perform(post("/quad/type")
                .param("sideA", "2")
                .param("sideB", "3")
                .param("sideC", "4")
                .param("sideD", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("Not a standard square/rectangle/rhombus based on side lengths."));
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
                // .andExpect(jsonPath("$.error").value("All inputs must be numeric and non-null."));
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
}
