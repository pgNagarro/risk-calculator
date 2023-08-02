package com.nagarro.riskcalculatorbackend.services.impl;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nagarro.riskcalculatorbackend.dtos.CompanyDimensionDto;
import com.nagarro.riskcalculatorbackend.models.CompanyDimension;
import com.nagarro.riskcalculatorbackend.models.Dimension;
import com.nagarro.riskcalculatorbackend.repositories.CompanyDimensionRepository;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CompanyDimensionServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CompanyDimensionServiceImplTest {
    @MockBean
    private CompanyDimensionRepository companyDimensionRepository;

    @Autowired
    private CompanyDimensionServiceImpl companyDimensionServiceImpl;

    /**
     * Method under test: {@link CompanyDimensionServiceImpl#getAllCompanyDimension()}
     */
    @Test
    void testGetAllCompanyDimension() {
        ArrayList<CompanyDimension> companyDimensionList = new ArrayList<>();
        when(companyDimensionRepository.findAll()).thenReturn(companyDimensionList);
        List<CompanyDimension> actualAllCompanyDimension = companyDimensionServiceImpl.getAllCompanyDimension();
        assertSame(companyDimensionList, actualAllCompanyDimension);
        assertTrue(actualAllCompanyDimension.isEmpty());
        verify(companyDimensionRepository).findAll();
    }


 

    /**
     * Method under test: {@link CompanyDimensionServiceImpl#saveCompanyDimension(CompanyDimensionDto)}
     */
    @Test
    void testSaveCompanyDimension4() throws IOException {
        CompanyDimension companyDimension = new CompanyDimension();
        companyDimension.setCompanyName("Company Name");
        companyDimension.setDimensions(new ArrayList<>());
        companyDimension.setId(1);
        when(companyDimensionRepository.save(Mockito.<CompanyDimension>any())).thenReturn(companyDimension);
        when(companyDimensionRepository.findByCompanyName(Mockito.<String>any())).thenReturn(new ArrayList<>());
        when(companyDimensionRepository.findAll()).thenReturn(new ArrayList<>());

        Dimension dimension = new Dimension();
        dimension.setDimensionName("start : saveCompanyDimension");
        dimension.setDimensionValue(42);

        ArrayList<Dimension> dimensionList = new ArrayList<>();
        dimensionList.add(dimension);
        CompanyDimensionDto companyDimensionDto = mock(CompanyDimensionDto.class);
        when(companyDimensionDto.getCompanyName()).thenReturn("Company Name");
        when(companyDimensionDto.getDimensions()).thenReturn(dimensionList);
        companyDimensionServiceImpl.saveCompanyDimension(companyDimensionDto);
        verify(companyDimensionRepository).save(Mockito.<CompanyDimension>any());
        verify(companyDimensionRepository).findByCompanyName(Mockito.<String>any());
        verify(companyDimensionRepository).findAll();
        verify(companyDimensionDto).getCompanyName();
        verify(companyDimensionDto).getDimensions();
    }

    /**
     * Method under test: {@link CompanyDimensionServiceImpl#saveCompanyDimension(CompanyDimensionDto)}
     */
    @Test
    void testSaveCompanyDimension5() throws IOException {
        CompanyDimension companyDimension = new CompanyDimension();
        companyDimension.setCompanyName("start : saveCompanyDimension");
        companyDimension.setDimensions(new ArrayList<>());
        companyDimension.setId(1);

        ArrayList<CompanyDimension> companyDimensionList = new ArrayList<>();
        companyDimensionList.add(companyDimension);
        when(companyDimensionRepository.findByCompanyName(Mockito.<String>any())).thenReturn(companyDimensionList);
        when(companyDimensionRepository.findAll()).thenReturn(new ArrayList<>());

        Dimension dimension = new Dimension();
        dimension.setDimensionName("start : saveCompanyDimension");
        dimension.setDimensionValue(42);

        ArrayList<Dimension> dimensionList = new ArrayList<>();
        dimensionList.add(dimension);
        CompanyDimensionDto companyDimensionDto = mock(CompanyDimensionDto.class);
        when(companyDimensionDto.getCompanyName()).thenReturn("Company Name");
        when(companyDimensionDto.getDimensions()).thenReturn(dimensionList);
        companyDimensionServiceImpl.saveCompanyDimension(companyDimensionDto);
        verify(companyDimensionRepository).findByCompanyName(Mockito.<String>any());
        verify(companyDimensionRepository).findAll();
        verify(companyDimensionDto).getCompanyName();
        verify(companyDimensionDto).getDimensions();
    }

    /**
     * Method under test: {@link CompanyDimensionServiceImpl#saveCompanyDimension(CompanyDimensionDto)}
     */
    @Test
    void testSaveCompanyDimension6() throws IOException {
        CompanyDimension companyDimension = new CompanyDimension();
        companyDimension.setCompanyName("Company Name");
        companyDimension.setDimensions(new ArrayList<>());
        companyDimension.setId(1);

        CompanyDimension companyDimension2 = new CompanyDimension();
        companyDimension2.setCompanyName("start : saveCompanyDimension");
        companyDimension2.setDimensions(new ArrayList<>());
        companyDimension2.setId(1);

        ArrayList<CompanyDimension> companyDimensionList = new ArrayList<>();
        companyDimensionList.add(companyDimension2);
        when(companyDimensionRepository.save(Mockito.<CompanyDimension>any())).thenReturn(companyDimension);
        when(companyDimensionRepository.findByCompanyName(Mockito.<String>any())).thenReturn(new ArrayList<>());
        when(companyDimensionRepository.findAll()).thenReturn(companyDimensionList);

        Dimension dimension = new Dimension();
        dimension.setDimensionName("start : saveCompanyDimension");
        dimension.setDimensionValue(42);

        ArrayList<Dimension> dimensionList = new ArrayList<>();
        dimensionList.add(dimension);
        CompanyDimensionDto companyDimensionDto = mock(CompanyDimensionDto.class);
        when(companyDimensionDto.getCompanyName()).thenReturn("Company Name");
        when(companyDimensionDto.getDimensions()).thenReturn(dimensionList);
        companyDimensionServiceImpl.saveCompanyDimension(companyDimensionDto);
        verify(companyDimensionRepository, atLeast(1)).save(Mockito.<CompanyDimension>any());
        verify(companyDimensionRepository).findByCompanyName(Mockito.<String>any());
        verify(companyDimensionRepository).findAll();
        verify(companyDimensionDto).getCompanyName();
        verify(companyDimensionDto).getDimensions();
        List<Dimension> dimensions = companyDimensionServiceImpl.getAllCompanyDimension().get(0).getDimensions();
        assertEquals(1, dimensions.size());
        Dimension getResult = dimensions.get(0);
        assertEquals("start : saveCompanyDimension", getResult.getDimensionName());
        assertEquals(0, getResult.getDimensionValue());
    }

    /**
     * Method under test: {@link CompanyDimensionServiceImpl#saveCompanyDimension(CompanyDimensionDto)}
     */
    @Test
    void testSaveCompanyDimension7() throws IOException {
        CompanyDimension companyDimension = new CompanyDimension();
        companyDimension.setCompanyName("Company Name");
        companyDimension.setDimensions(new ArrayList<>());
        companyDimension.setId(1);

        CompanyDimension companyDimension2 = new CompanyDimension();
        companyDimension2.setCompanyName("start : saveCompanyDimension");
        companyDimension2.setDimensions(new ArrayList<>());
        companyDimension2.setId(1);

        ArrayList<CompanyDimension> companyDimensionList = new ArrayList<>();
        companyDimensionList.add(companyDimension2);

        CompanyDimension companyDimension3 = new CompanyDimension();
        companyDimension3.setCompanyName("Company Name");
        companyDimension3.setDimensions(new ArrayList<>());
        companyDimension3.setId(3);

        ArrayList<CompanyDimension> companyDimensionList2 = new ArrayList<>();
        companyDimensionList2.add(companyDimension3);
        when(companyDimensionRepository.save(Mockito.<CompanyDimension>any())).thenReturn(companyDimension);
        when(companyDimensionRepository.findByCompanyName(Mockito.<String>any())).thenReturn(companyDimensionList);
        when(companyDimensionRepository.findAll()).thenReturn(companyDimensionList2);

        Dimension dimension = new Dimension();
        dimension.setDimensionName("start : saveCompanyDimension");
        dimension.setDimensionValue(42);

        ArrayList<Dimension> dimensionList = new ArrayList<>();
        dimensionList.add(dimension);
        CompanyDimensionDto companyDimensionDto = mock(CompanyDimensionDto.class);
        when(companyDimensionDto.getCompanyName()).thenReturn("Company Name");
        when(companyDimensionDto.getDimensions()).thenReturn(dimensionList);
        companyDimensionServiceImpl.saveCompanyDimension(companyDimensionDto);
        verify(companyDimensionRepository).save(Mockito.<CompanyDimension>any());
        verify(companyDimensionRepository).findByCompanyName(Mockito.<String>any());
        verify(companyDimensionRepository, atLeast(1)).findAll();
        verify(companyDimensionDto).getCompanyName();
        verify(companyDimensionDto).getDimensions();
        List<Dimension> dimensions = companyDimensionServiceImpl.getAllCompanyDimension().get(0).getDimensions();
        assertEquals(1, dimensions.size());
        Dimension getResult = dimensions.get(0);
        assertEquals("start : saveCompanyDimension", getResult.getDimensionName());
        assertEquals(42, getResult.getDimensionValue());
    }

    /**
     * Method under test: {@link CompanyDimensionServiceImpl#saveCompanyDimension(CompanyDimensionDto)}
     */
    @Test
    void testSaveCompanyDimension8() throws IOException {
        CompanyDimension companyDimension = new CompanyDimension();
        companyDimension.setCompanyName("Company Name");
        companyDimension.setDimensions(new ArrayList<>());
        companyDimension.setId(1);

        CompanyDimension companyDimension2 = new CompanyDimension();
        companyDimension2.setCompanyName("start : saveCompanyDimension");
        companyDimension2.setDimensions(new ArrayList<>());
        companyDimension2.setId(1);

        ArrayList<CompanyDimension> companyDimensionList = new ArrayList<>();
        companyDimensionList.add(companyDimension2);

        CompanyDimension companyDimension3 = new CompanyDimension();
        companyDimension3.setCompanyName("Company Name");
        companyDimension3.setDimensions(new ArrayList<>());
        companyDimension3.setId(3);

        CompanyDimension companyDimension4 = new CompanyDimension();
        companyDimension4.setCompanyName("start : saveCompanyDimension");
        companyDimension4.setDimensions(new ArrayList<>());
        companyDimension4.setId(1);

        ArrayList<CompanyDimension> companyDimensionList2 = new ArrayList<>();
        companyDimensionList2.add(companyDimension4);
        companyDimensionList2.add(companyDimension3);
        when(companyDimensionRepository.save(Mockito.<CompanyDimension>any())).thenReturn(companyDimension);
        when(companyDimensionRepository.findByCompanyName(Mockito.<String>any())).thenReturn(companyDimensionList);
        when(companyDimensionRepository.findAll()).thenReturn(companyDimensionList2);

        Dimension dimension = new Dimension();
        dimension.setDimensionName("start : saveCompanyDimension");
        dimension.setDimensionValue(42);

        ArrayList<Dimension> dimensionList = new ArrayList<>();
        dimensionList.add(dimension);
        CompanyDimensionDto companyDimensionDto = mock(CompanyDimensionDto.class);
        when(companyDimensionDto.getCompanyName()).thenReturn("Company Name");
        when(companyDimensionDto.getDimensions()).thenReturn(dimensionList);
        companyDimensionServiceImpl.saveCompanyDimension(companyDimensionDto);
        verify(companyDimensionRepository, atLeast(1)).save(Mockito.<CompanyDimension>any());
        verify(companyDimensionRepository).findByCompanyName(Mockito.<String>any());
        verify(companyDimensionRepository, atLeast(1)).findAll();
        verify(companyDimensionDto).getCompanyName();
        verify(companyDimensionDto).getDimensions();
        List<CompanyDimension> allCompanyDimension = companyDimensionServiceImpl.getAllCompanyDimension();
        List<Dimension> dimensions = allCompanyDimension.get(1).getDimensions();
        assertEquals(1, dimensions.size());
        List<Dimension> dimensions2 = allCompanyDimension.get(0).getDimensions();
        assertEquals(1, dimensions2.size());
        Dimension getResult = dimensions.get(0);
        assertEquals(42, getResult.getDimensionValue());
        Dimension getResult2 = dimensions2.get(0);
        assertEquals(0, getResult2.getDimensionValue());
        assertEquals("start : saveCompanyDimension", getResult2.getDimensionName());
        assertEquals("start : saveCompanyDimension", getResult.getDimensionName());
    }

    /**
     * Method under test: {@link CompanyDimensionServiceImpl#saveCompanyDimension(CompanyDimensionDto)}
     */
    @Test
    void testSaveCompanyDimension9() throws IOException {
        CompanyDimension companyDimension = new CompanyDimension();
        companyDimension.setCompanyName("Company Name");
        companyDimension.setDimensions(new ArrayList<>());
        companyDimension.setId(1);
        CompanyDimension companyDimension2 = mock(CompanyDimension.class);
        when(companyDimension2.getCompanyName()).thenReturn("Company Name");
        when(companyDimension2.getDimensions()).thenReturn(new ArrayList<>());
        doNothing().when(companyDimension2).setCompanyName(Mockito.<String>any());
        doNothing().when(companyDimension2).setDimensions(Mockito.<List<Dimension>>any());
        doNothing().when(companyDimension2).setId(anyInt());
        companyDimension2.setCompanyName("start : saveCompanyDimension");
        companyDimension2.setDimensions(new ArrayList<>());
        companyDimension2.setId(1);

        ArrayList<CompanyDimension> companyDimensionList = new ArrayList<>();
        companyDimensionList.add(companyDimension2);
        when(companyDimensionRepository.save(Mockito.<CompanyDimension>any())).thenReturn(companyDimension);
        when(companyDimensionRepository.findByCompanyName(Mockito.<String>any())).thenReturn(new ArrayList<>());
        when(companyDimensionRepository.findAll()).thenReturn(companyDimensionList);

        Dimension dimension = new Dimension();
        dimension.setDimensionName("start : saveCompanyDimension");
        dimension.setDimensionValue(42);

        ArrayList<Dimension> dimensionList = new ArrayList<>();
        dimensionList.add(dimension);
        CompanyDimensionDto companyDimensionDto = mock(CompanyDimensionDto.class);
        when(companyDimensionDto.getCompanyName()).thenReturn("Company Name");
        when(companyDimensionDto.getDimensions()).thenReturn(dimensionList);
        companyDimensionServiceImpl.saveCompanyDimension(companyDimensionDto);
        verify(companyDimensionRepository).save(Mockito.<CompanyDimension>any());
        verify(companyDimensionRepository).findByCompanyName(Mockito.<String>any());
        verify(companyDimensionRepository).findAll();
        verify(companyDimension2).getCompanyName();
        verify(companyDimension2).getDimensions();
        verify(companyDimension2).setCompanyName(Mockito.<String>any());
        verify(companyDimension2).setDimensions(Mockito.<List<Dimension>>any());
        verify(companyDimension2).setId(anyInt());
        verify(companyDimensionDto).getCompanyName();
        verify(companyDimensionDto).getDimensions();
    }

    /**
     * Method under test: {@link CompanyDimensionServiceImpl#saveCompanyDimension(CompanyDimensionDto)}
     */
    @Test
    void testSaveCompanyDimension10() throws IOException {
        CompanyDimension companyDimension = new CompanyDimension();
        companyDimension.setCompanyName("Company Name");
        companyDimension.setDimensions(new ArrayList<>());
        companyDimension.setId(1);

        Dimension dimension = new Dimension();
        dimension.setDimensionName("start : saveCompanyDimension");
        dimension.setDimensionValue(42);

        ArrayList<Dimension> dimensionList = new ArrayList<>();
        dimensionList.add(dimension);
        CompanyDimension companyDimension2 = mock(CompanyDimension.class);
        when(companyDimension2.getDimensions()).thenReturn(dimensionList);
        doNothing().when(companyDimension2).setCompanyName(Mockito.<String>any());
        doNothing().when(companyDimension2).setDimensions(Mockito.<List<Dimension>>any());
        doNothing().when(companyDimension2).setId(anyInt());
        companyDimension2.setCompanyName("start : saveCompanyDimension");
        companyDimension2.setDimensions(new ArrayList<>());
        companyDimension2.setId(1);

        ArrayList<CompanyDimension> companyDimensionList = new ArrayList<>();
        companyDimensionList.add(companyDimension2);
        when(companyDimensionRepository.save(Mockito.<CompanyDimension>any())).thenReturn(companyDimension);
        when(companyDimensionRepository.findByCompanyName(Mockito.<String>any())).thenReturn(new ArrayList<>());
        when(companyDimensionRepository.findAll()).thenReturn(companyDimensionList);

        Dimension dimension2 = new Dimension();
        dimension2.setDimensionName("start : saveCompanyDimension");
        dimension2.setDimensionValue(42);

        ArrayList<Dimension> dimensionList2 = new ArrayList<>();
        dimensionList2.add(dimension2);
        CompanyDimensionDto companyDimensionDto = mock(CompanyDimensionDto.class);
        when(companyDimensionDto.getCompanyName()).thenReturn("Company Name");
        when(companyDimensionDto.getDimensions()).thenReturn(dimensionList2);
        companyDimensionServiceImpl.saveCompanyDimension(companyDimensionDto);
        verify(companyDimensionRepository).save(Mockito.<CompanyDimension>any());
        verify(companyDimensionRepository).findByCompanyName(Mockito.<String>any());
        verify(companyDimensionRepository).findAll();
        verify(companyDimension2).getDimensions();
        verify(companyDimension2).setCompanyName(Mockito.<String>any());
        verify(companyDimension2).setDimensions(Mockito.<List<Dimension>>any());
        verify(companyDimension2).setId(anyInt());
        verify(companyDimensionDto).getCompanyName();
        verify(companyDimensionDto).getDimensions();
    }

    /**
     * Method under test: {@link CompanyDimensionServiceImpl#saveCompanyDimension(CompanyDimensionDto)}
     */
    @Test
    void testSaveCompanyDimension11() throws IOException {
        CompanyDimension companyDimension = new CompanyDimension();
        companyDimension.setCompanyName("Company Name");
        companyDimension.setDimensions(new ArrayList<>());
        companyDimension.setId(1);

        Dimension dimension = new Dimension();
        dimension.setDimensionName("start : saveCompanyDimension");
        dimension.setDimensionValue(42);

        Dimension dimension2 = new Dimension();
        dimension2.setDimensionName("start : checkDataIfPresent");
        dimension2.setDimensionValue(12);

        ArrayList<Dimension> dimensionList = new ArrayList<>();
        dimensionList.add(dimension2);
        dimensionList.add(dimension);
        CompanyDimension companyDimension2 = mock(CompanyDimension.class);
        when(companyDimension2.getDimensions()).thenReturn(dimensionList);
        doNothing().when(companyDimension2).setCompanyName(Mockito.<String>any());
        doNothing().when(companyDimension2).setDimensions(Mockito.<List<Dimension>>any());
        doNothing().when(companyDimension2).setId(anyInt());
        companyDimension2.setCompanyName("start : saveCompanyDimension");
        companyDimension2.setDimensions(new ArrayList<>());
        companyDimension2.setId(1);

        ArrayList<CompanyDimension> companyDimensionList = new ArrayList<>();
        companyDimensionList.add(companyDimension2);
        when(companyDimensionRepository.save(Mockito.<CompanyDimension>any())).thenReturn(companyDimension);
        when(companyDimensionRepository.findByCompanyName(Mockito.<String>any())).thenReturn(new ArrayList<>());
        when(companyDimensionRepository.findAll()).thenReturn(companyDimensionList);

        Dimension dimension3 = new Dimension();
        dimension3.setDimensionName("start : saveCompanyDimension");
        dimension3.setDimensionValue(42);

        ArrayList<Dimension> dimensionList2 = new ArrayList<>();
        dimensionList2.add(dimension3);
        CompanyDimensionDto companyDimensionDto = mock(CompanyDimensionDto.class);
        when(companyDimensionDto.getCompanyName()).thenReturn("Company Name");
        when(companyDimensionDto.getDimensions()).thenReturn(dimensionList2);
        companyDimensionServiceImpl.saveCompanyDimension(companyDimensionDto);
        verify(companyDimensionRepository).save(Mockito.<CompanyDimension>any());
        verify(companyDimensionRepository).findByCompanyName(Mockito.<String>any());
        verify(companyDimensionRepository).findAll();
        verify(companyDimension2).getDimensions();
        verify(companyDimension2).setCompanyName(Mockito.<String>any());
        verify(companyDimension2).setDimensions(Mockito.<List<Dimension>>any());
        verify(companyDimension2).setId(anyInt());
        verify(companyDimensionDto).getCompanyName();
        verify(companyDimensionDto).getDimensions();
    }

    /**
     * Method under test: {@link CompanyDimensionServiceImpl#getCompanyDimensionByCompanyName(String)}
     */
    @Test
    void testGetCompanyDimensionByCompanyName() throws IOException {
        when(companyDimensionRepository.findByCompanyName(Mockito.<String>any())).thenReturn(new ArrayList<>());
        assertThrows(IOException.class, () -> companyDimensionServiceImpl.getCompanyDimensionByCompanyName("Company Name"));
        verify(companyDimensionRepository).findByCompanyName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CompanyDimensionServiceImpl#getCompanyDimensionByCompanyName(String)}
     */
    @Test
    void testGetCompanyDimensionByCompanyName2() throws IOException {
        CompanyDimension companyDimension = new CompanyDimension();
        companyDimension.setCompanyName("start : getCompanyDimensionByCompanyName");
        companyDimension.setDimensions(new ArrayList<>());
        companyDimension.setId(1);

        ArrayList<CompanyDimension> companyDimensionList = new ArrayList<>();
        companyDimensionList.add(companyDimension);
        when(companyDimensionRepository.findByCompanyName(Mockito.<String>any())).thenReturn(companyDimensionList);
        CompanyDimensionDto actualCompanyDimensionByCompanyName = companyDimensionServiceImpl
                .getCompanyDimensionByCompanyName("Company Name");
        assertEquals("start : getCompanyDimensionByCompanyName", actualCompanyDimensionByCompanyName.getCompanyName());
        assertTrue(actualCompanyDimensionByCompanyName.getDimensions().isEmpty());
        verify(companyDimensionRepository).findByCompanyName(Mockito.<String>any());
    }


    /**
     * Method under test: {@link CompanyDimensionServiceImpl#updateCompanyDimension(CompanyDimensionDto)}
     */
    @Test
    void testUpdateCompanyDimension2() {
        CompanyDimension companyDimension = new CompanyDimension();
        companyDimension.setCompanyName("start : updateCompanyDimension");
        companyDimension.setDimensions(new ArrayList<>());
        companyDimension.setId(1);

        ArrayList<CompanyDimension> companyDimensionList = new ArrayList<>();
        companyDimensionList.add(companyDimension);

        CompanyDimension companyDimension2 = new CompanyDimension();
        companyDimension2.setCompanyName("Company Name");
        companyDimension2.setDimensions(new ArrayList<>());
        companyDimension2.setId(1);
        when(companyDimensionRepository.save(Mockito.<CompanyDimension>any())).thenReturn(companyDimension2);
        when(companyDimensionRepository.findByCompanyName(Mockito.<String>any())).thenReturn(companyDimensionList);
        CompanyDimensionDto companyDimensionDto = new CompanyDimensionDto();
        assertSame(companyDimensionDto, companyDimensionServiceImpl.updateCompanyDimension(companyDimensionDto));
        verify(companyDimensionRepository).save(Mockito.<CompanyDimension>any());
        verify(companyDimensionRepository).findByCompanyName(Mockito.<String>any());
    }

    


    /**
     * Method under test: {@link CompanyDimensionServiceImpl#deleteCompanyDimension(CompanyDimensionDto)}
     */
    @Test
    void testDeleteCompanyDimension2() {
        CompanyDimension companyDimension = new CompanyDimension();
        companyDimension.setCompanyName("start : deleteCompanyDimension");
        companyDimension.setDimensions(new ArrayList<>());
        companyDimension.setId(1);

        ArrayList<CompanyDimension> companyDimensionList = new ArrayList<>();
        companyDimensionList.add(companyDimension);
        doNothing().when(companyDimensionRepository).deleteById(Mockito.<Integer>any());
        when(companyDimensionRepository.findByCompanyName(Mockito.<String>any())).thenReturn(companyDimensionList);
        companyDimensionServiceImpl.deleteCompanyDimension(new CompanyDimensionDto());
        verify(companyDimensionRepository).findByCompanyName(Mockito.<String>any());
        verify(companyDimensionRepository).deleteById(Mockito.<Integer>any());
        assertTrue(companyDimensionServiceImpl.getAllCompanyDimension().isEmpty());
    }


    /**
     * Method under test: {@link CompanyDimensionServiceImpl#checkDataIfPresent(CompanyDimension)}
     */
    @Test
    void testCheckDataIfPresent() {
        when(companyDimensionRepository.findByCompanyName(Mockito.<String>any())).thenReturn(new ArrayList<>());

        CompanyDimension companyDimension = new CompanyDimension();
        companyDimension.setCompanyName("Company Name");
        companyDimension.setDimensions(new ArrayList<>());
        companyDimension.setId(1);
        assertTrue(companyDimensionServiceImpl.checkDataIfPresent(companyDimension));
        verify(companyDimensionRepository).findByCompanyName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CompanyDimensionServiceImpl#checkDataIfPresent(CompanyDimension)}
     */
    @Test
    void testCheckDataIfPresent2() {
        CompanyDimension companyDimension = new CompanyDimension();
        companyDimension.setCompanyName("start : checkDataIfPresent");
        companyDimension.setDimensions(new ArrayList<>());
        companyDimension.setId(1);

        ArrayList<CompanyDimension> companyDimensionList = new ArrayList<>();
        companyDimensionList.add(companyDimension);
        when(companyDimensionRepository.findByCompanyName(Mockito.<String>any())).thenReturn(companyDimensionList);

        CompanyDimension companyDimension2 = new CompanyDimension();
        companyDimension2.setCompanyName("Company Name");
        companyDimension2.setDimensions(new ArrayList<>());
        companyDimension2.setId(1);
        assertFalse(companyDimensionServiceImpl.checkDataIfPresent(companyDimension2));
        verify(companyDimensionRepository).findByCompanyName(Mockito.<String>any());
    }
}

