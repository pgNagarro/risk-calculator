package com.nagarro.riskcalculatorbackend.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.riskcalculatorbackend.dtos.CalculationLogicDto;
import com.nagarro.riskcalculatorbackend.services.CalculationLogicService;

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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CalculationLogicController.class})
@ExtendWith(SpringExtension.class)
class CalculationLogicControllerTest {
    @Autowired
    private CalculationLogicController calculationLogicController;

    @MockBean
    private CalculationLogicService calculationLogicService;

    /**
     * Method under test: {@link CalculationLogicController#deleteRiskCalc(String)}
     */
    @Test
    void testDeleteRiskCalc() throws Exception {
        // Arrange
        when(calculationLogicService.getCalculationLogicByName(Mockito.<String>any()))
                .thenReturn(new CalculationLogicDto("Element Name", "Formula"));
        doNothing().when(calculationLogicService).deleteCalculationLogic(Mockito.<CalculationLogicDto>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/calculation-logic/{elementName}",
                "Element Name");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(calculationLogicController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"Deleted\":true}"));
    }

    /**
     * Method under test: {@link CalculationLogicController#deleteRiskCalc(String)}
     */
    @Test
    void testDeleteRiskCalc2() throws Exception {
        // Arrange
        when(calculationLogicService.getCalculationLogicByName(Mockito.<String>any())).thenThrow(new IOException("?"));
        doNothing().when(calculationLogicService).deleteCalculationLogic(Mockito.<CalculationLogicDto>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/calculation-logic/{elementName}",
                "Element Name");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(calculationLogicController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"Unable to Delete\":false}"));
    }

    /**
     * Method under test: {@link CalculationLogicController#getAllRiskCalcLogic()}
     */
    @Test
    void testGetAllRiskCalcLogic() throws Exception {
        // Arrange
        when(calculationLogicService.getAllCalculationLogic()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/all-calculation-logic");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(calculationLogicController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CalculationLogicController#getAllRiskCalcLogic()}
     */
    @Test
    void testGetAllRiskCalcLogic2() throws Exception {
        // Arrange
        when(calculationLogicService.getAllCalculationLogic()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/all-calculation-logic");
        requestBuilder.characterEncoding("Encoding");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(calculationLogicController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CalculationLogicController#getRiskCalcByElementName(String)}
     */
    @Test
    void testGetRiskCalcByElementName() throws Exception {
        // Arrange
        when(calculationLogicService.getCalculationLogicByName(Mockito.<String>any()))
                .thenReturn(new CalculationLogicDto("Element Name", "Formula"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/calculation-logic/{elementName}",
                "Element Name");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(calculationLogicController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"elementName\":\"Element Name\",\"formula\":\"Formula\"}"));
    }

    /**
     * Method under test: {@link CalculationLogicController#getRiskCalcByElementName(String)}
     */
    @Test
    void testGetRiskCalcByElementName2() throws Exception {
        // Arrange
        when(calculationLogicService.getCalculationLogicByName(Mockito.<String>any())).thenThrow(new IOException("?"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/calculation-logic/{elementName}",
                "Element Name");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(calculationLogicController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link CalculationLogicController#saveRiskCalcLogic(CalculationLogicDto)}
     */
    @Test
    void testSaveRiskCalcLogic() throws Exception {
        // Arrange
        when(calculationLogicService.saveCalculationLogic(Mockito.<CalculationLogicDto>any()))
                .thenReturn(new CalculationLogicDto("Element Name", "Formula"));

        CalculationLogicDto calculationLogicDto = new CalculationLogicDto();
        calculationLogicDto.setElementName("Element Name");
        calculationLogicDto.setFormula("Formula");
        String content = (new ObjectMapper()).writeValueAsString(calculationLogicDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/add-calculation-logic")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(calculationLogicController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"elementName\":\"Element Name\",\"formula\":\"Formula\"}"));
    }

    /**
     * Method under test: {@link CalculationLogicController#updateRiskCalc(String, CalculationLogicDto)}
     */
    @Test
    void testUpdateRiskCalc() throws Exception {
        // Arrange
        when(calculationLogicService.updateCalculationLogic(Mockito.<CalculationLogicDto>any()))
                .thenReturn(new CalculationLogicDto("Element Name", "Formula"));

        CalculationLogicDto calculationLogicDto = new CalculationLogicDto();
        calculationLogicDto.setElementName("Element Name");
        calculationLogicDto.setFormula("Formula");
        String content = (new ObjectMapper()).writeValueAsString(calculationLogicDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/calculation-logic/{elementName}", "Element Name")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(calculationLogicController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"elementName\":\"Element Name\",\"formula\":\"Formula\"}"));
    }
}

