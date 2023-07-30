package com.nagarro.riskcalculatorbackend.controllers;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.riskcalculatorbackend.dtos.DimensionWeightDto;
import com.nagarro.riskcalculatorbackend.services.DimensionWeightService;

import java.io.IOException;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {DimensionWeightController.class})
@ExtendWith(SpringExtension.class)
class DimensionWeightControllerTest {
    @Autowired
    private DimensionWeightController dimensionWeightController;

    @MockBean
    private DimensionWeightService dimensionWeightService;

    /**
     * Method under test: {@link DimensionWeightController#getDimensionWeight()}
     */
    @Test
    void testGetDimensionWeight() throws Exception {
        // Arrange
        when(dimensionWeightService.getAllDimensionWeight()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/all-dimension-weight");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(dimensionWeightController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link DimensionWeightController#getDimensionWeight()}
     */
    @Test
    void testGetDimensionWeight2() throws Exception {
        // Arrange
        when(dimensionWeightService.getAllDimensionWeight()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/all-dimension-weight");
        requestBuilder.characterEncoding("Encoding");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(dimensionWeightController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link DimensionWeightController#saveDimensionWeightDto(DimensionWeightDto)}
     */
    @Test
    void testSaveDimensionWeightDto() throws Exception {
        // Arrange
        when(dimensionWeightService.saveDimensionWeight(Mockito.<DimensionWeightDto>any()))
                .thenReturn(new DimensionWeightDto());

        DimensionWeightDto dimensionWeightDto = new DimensionWeightDto();
        dimensionWeightDto.setDimensionWeights(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(dimensionWeightDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/add-dimension-weight")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(dimensionWeightController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"dimensionWeights\":null}"));
    }

    /**
     * Method under test: {@link DimensionWeightController#saveDimensionWeightDto(DimensionWeightDto)}
     */
    @Test
    void testSaveDimensionWeightDto2() throws Exception {
        // Arrange
        when(dimensionWeightService.saveDimensionWeight(Mockito.<DimensionWeightDto>any()))
                .thenThrow(new IOException("?"));

        DimensionWeightDto dimensionWeightDto = new DimensionWeightDto();
        dimensionWeightDto.setDimensionWeights(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(dimensionWeightDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/add-dimension-weight")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(dimensionWeightController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
    }
}

