package com.nagarro.riskcalculatorbackend.controllers;


import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.riskcalculatorbackend.dtos.CompanyDimensionDto;
import com.nagarro.riskcalculatorbackend.services.CompanyDimensionService;

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

@ContextConfiguration(classes = {CompanyDimensionController.class})
@ExtendWith(SpringExtension.class)
class CompanyDimensionControllerTest {
    @Autowired
    private CompanyDimensionController companyDimensionController;

    @MockBean
    private CompanyDimensionService companyDimensionService;

    /**
     * Method under test: {@link CompanyDimensionController#saveCompanyDimension(CompanyDimensionDto)}
     */
    @Test
    void testSaveCompanyDimension() throws Exception {
        doNothing().when(companyDimensionService).saveCompanyDimension(Mockito.<CompanyDimensionDto>any());

        CompanyDimensionDto companyDimensionDto = new CompanyDimensionDto();
        companyDimensionDto.setCompanyName("Company Name");
        companyDimensionDto.setDimensions(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(companyDimensionDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/add-company-dimension")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(companyDimensionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"companyName\":\"Company Name\",\"dimensions\":[]}"));
    }

    /**
     * Method under test: {@link CompanyDimensionController#saveCompanyDimension(CompanyDimensionDto)}
     */
    @Test
    void testSaveCompanyDimension2() throws Exception {
        doThrow(new IOException("?")).when(companyDimensionService)
                .saveCompanyDimension(Mockito.<CompanyDimensionDto>any());

        CompanyDimensionDto companyDimensionDto = new CompanyDimensionDto();
        companyDimensionDto.setCompanyName("Company Name");
        companyDimensionDto.setDimensions(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(companyDimensionDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/add-company-dimension")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(companyDimensionController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
    }

    /**
     * Method under test: {@link CompanyDimensionController#deleteDimension(String)}
     */
    @Test
    void testDeleteDimension() throws Exception {
        when(companyDimensionService.getCompanyDimensionByCompanyName(Mockito.<String>any()))
                .thenReturn(new CompanyDimensionDto());
        doNothing().when(companyDimensionService).deleteCompanyDimension(Mockito.<CompanyDimensionDto>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/company-dimension/{companyName}",
                "Company Name");
        MockMvcBuilders.standaloneSetup(companyDimensionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"Deleted\":true}"));
    }

    /**
     * Method under test: {@link CompanyDimensionController#getCompanyDimensionByCompanyName(String)}
     */
    @Test
    void testGetCompanyDimensionByCompanyName() throws Exception {
        when(companyDimensionService.getCompanyDimensionByCompanyName(Mockito.<String>any()))
                .thenReturn(new CompanyDimensionDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/company-dimension/{companyName}",
                "Company Name");
        MockMvcBuilders.standaloneSetup(companyDimensionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"companyName\":null,\"dimensions\":null}"));
    }

    /**
     * Method under test: {@link CompanyDimensionController#getCompanyDimensionByCompanyName(String)}
     */
    @Test
    void testGetCompanyDimensionByCompanyName2() throws Exception {
        when(companyDimensionService.getCompanyDimensionByCompanyName(Mockito.<String>any()))
                .thenThrow(new IOException("?"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/company-dimension/{companyName}",
                "Company Name");
        MockMvcBuilders.standaloneSetup(companyDimensionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link CompanyDimensionController#getCompanyDimensionByCompanyName(String)}
     */
    @Test
    void testGetCompanyDimensionByCompanyName3() throws Exception {
        when(companyDimensionService.getAllCompanyDimension()).thenReturn(new ArrayList<>());
        when(companyDimensionService.getCompanyDimensionByCompanyName(Mockito.<String>any()))
                .thenReturn(new CompanyDimensionDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/company-dimension/{companyName}", "",
                "Uri Variables");
        MockMvcBuilders.standaloneSetup(companyDimensionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CompanyDimensionController#updateCompanyDimension(String, CompanyDimensionDto)}
     */
    @Test
    void testUpdateCompanyDimension() throws Exception {
        when(companyDimensionService.updateCompanyDimension(Mockito.<CompanyDimensionDto>any()))
                .thenReturn(new CompanyDimensionDto());

        CompanyDimensionDto companyDimensionDto = new CompanyDimensionDto();
        companyDimensionDto.setCompanyName("Company Name");
        companyDimensionDto.setDimensions(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(companyDimensionDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/company-dimension/{companyName}", "Company Name")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(companyDimensionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"companyName\":null,\"dimensions\":null}"));
    }

    /**
     * Method under test: {@link CompanyDimensionController#deleteDimension(String)}
     */
    @Test
    void testDeleteDimension2() throws Exception {
        when(companyDimensionService.getCompanyDimensionByCompanyName(Mockito.<String>any()))
                .thenThrow(new IOException("?"));
        doNothing().when(companyDimensionService).deleteCompanyDimension(Mockito.<CompanyDimensionDto>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/company-dimension/{companyName}",
                "Company Name");
        MockMvcBuilders.standaloneSetup(companyDimensionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"Unable to Delete\":false}"));
    }

    /**
     * Method under test: {@link CompanyDimensionController#getCompanyDimension()}
     */
    @Test
    void testGetCompanyDimension() throws Exception {
        when(companyDimensionService.getAllCompanyDimension()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/company-dimension");
        MockMvcBuilders.standaloneSetup(companyDimensionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CompanyDimensionController#getCompanyDimension()}
     */
    @Test
    void testGetCompanyDimension2() throws Exception {
        when(companyDimensionService.getAllCompanyDimension()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/company-dimension");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(companyDimensionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
