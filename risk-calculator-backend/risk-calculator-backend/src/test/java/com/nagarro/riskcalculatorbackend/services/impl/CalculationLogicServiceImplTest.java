package com.nagarro.riskcalculatorbackend.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nagarro.riskcalculatorbackend.dtos.CalculationLogicDto;
import com.nagarro.riskcalculatorbackend.models.CalculationLogic;
import com.nagarro.riskcalculatorbackend.repositories.CalculationLogicRepository;

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

@ContextConfiguration(classes = {CalculationLogicServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CalculationLogicServiceImplTest {
    @MockBean
    private CalculationLogicRepository calculationLogicRepository;

    @Autowired
    private CalculationLogicServiceImpl calculationLogicServiceImpl;

    /**
     * Method under test: {@link CalculationLogicServiceImpl#getAllCalculationLogic()}
     */
    @Test
    void testGetAllCalculationLogic() {
        // Arrange
        ArrayList<CalculationLogic> calculationLogicList = new ArrayList<>();
        when(calculationLogicRepository.findAll()).thenReturn(calculationLogicList);

        // Act
        List<CalculationLogic> actualAllCalculationLogic = calculationLogicServiceImpl.getAllCalculationLogic();

        // Assert
        assertSame(calculationLogicList, actualAllCalculationLogic);
        assertTrue(actualAllCalculationLogic.isEmpty());
        verify(calculationLogicRepository).findAll();
    }

    /**
     * Method under test: {@link CalculationLogicServiceImpl#saveCalculationLogic(CalculationLogicDto)}
     */
    @Test
    void testSaveCalculationLogic() {
        // Arrange
        CalculationLogic calculationLogic = new CalculationLogic();
        calculationLogic.setElementName("Element Name");
        calculationLogic.setFormula("Formula");
        when(calculationLogicRepository.save(Mockito.<CalculationLogic>any())).thenReturn(calculationLogic);
        CalculationLogicDto calculationLogicDto = new CalculationLogicDto("Element Name", "Formula");

        // Act and Assert
        assertSame(calculationLogicDto, calculationLogicServiceImpl.saveCalculationLogic(calculationLogicDto));
        verify(calculationLogicRepository).save(Mockito.<CalculationLogic>any());
    }

  

    /**
     * Method under test: {@link CalculationLogicServiceImpl#getCalculationLogicByName(String)}
     */
    @Test
    void testGetCalculationLogicByName() throws IOException {
        // Arrange
        CalculationLogic calculationLogic = new CalculationLogic();
        calculationLogic.setElementName("Element Name");
        calculationLogic.setFormula("Formula");
        when(calculationLogicRepository.findByElementName(Mockito.<String>any())).thenReturn(calculationLogic);

        // Act
        CalculationLogicDto actualCalculationLogicByName = calculationLogicServiceImpl.getCalculationLogicByName("Name");

        // Assert
        assertEquals("Element Name", actualCalculationLogicByName.getElementName());
        assertEquals("Formula", actualCalculationLogicByName.getFormula());
        verify(calculationLogicRepository).findByElementName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CalculationLogicServiceImpl#updateCalculationLogic(CalculationLogicDto)}
     */
    @Test
    void testUpdateCalculationLogic() {
        // Arrange
        CalculationLogic calculationLogic = new CalculationLogic();
        calculationLogic.setElementName("Element Name");
        calculationLogic.setFormula("Formula");

        CalculationLogic calculationLogic2 = new CalculationLogic();
        calculationLogic2.setElementName("Element Name");
        calculationLogic2.setFormula("Formula");
        when(calculationLogicRepository.save(Mockito.<CalculationLogic>any())).thenReturn(calculationLogic2);
        when(calculationLogicRepository.findByElementName(Mockito.<String>any())).thenReturn(calculationLogic);

        // Act
        CalculationLogicDto actualUpdateCalculationLogicResult = calculationLogicServiceImpl
                .updateCalculationLogic(new CalculationLogicDto("Element Name", "Formula"));

        // Assert
        assertEquals("Element Name", actualUpdateCalculationLogicResult.getElementName());
        assertEquals("Formula", actualUpdateCalculationLogicResult.getFormula());
        verify(calculationLogicRepository).findByElementName(Mockito.<String>any());
        verify(calculationLogicRepository).save(Mockito.<CalculationLogic>any());
    }


    /**
     * Method under test: {@link CalculationLogicServiceImpl#deleteCalculationLogic(CalculationLogicDto)}
     */
    @Test
    void testDeleteCalculationLogic() {
        // Arrange
        doNothing().when(calculationLogicRepository).deleteById(Mockito.<String>any());
        CalculationLogicDto calculationLogicDto = new CalculationLogicDto("Element Name", "Formula");

        // Act
        calculationLogicServiceImpl.deleteCalculationLogic(calculationLogicDto);

        // Assert that nothing has changed
        verify(calculationLogicRepository).deleteById(Mockito.<String>any());
        assertEquals("Element Name", calculationLogicDto.getElementName());
        assertEquals("Formula", calculationLogicDto.getFormula());
        assertTrue(calculationLogicServiceImpl.getAllCalculationLogic().isEmpty());
    }


}

