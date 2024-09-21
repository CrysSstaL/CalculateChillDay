package org.calc.calculator.service;

import org.calc.calculator.dto.requests.CalculateDtoRequest;
import org.calc.calculator.dto.responses.CalculateDtoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalculateServiceTests {

    @InjectMocks
    private CalculateServiceImpl calculateService;

    private CalculateDtoRequest calculateDtoRequest;
    private Date date;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    // 100000 / 29.3 * 28 = 95563,16
    @Test
    public void testToTrueCalculate_dateIsNull() throws IOException {
        calculateDtoRequest
                = new CalculateDtoRequest(new BigDecimal(100000), 28);
        CalculateDtoResponse calculateDtoResponse =
                calculateService.calculate(calculateDtoRequest, null);
        assertEquals(calculateDtoResponse.getSumOfChillDaysMoney(), BigDecimal.valueOf(95563.16));
    }

    // 100000 / 29.3 * 14 = 47781.58
    @Test
    public void testToTrueCalculate_dateIsNotNull() throws IOException, ParseException {
        calculateDtoRequest
                = new CalculateDtoRequest(new BigDecimal(100000), 28);
        date = new SimpleDateFormat("yyyy-MM-dd").parse("2024-01-01");
        CalculateDtoResponse calculateDtoResponse =
                calculateService.calculate(calculateDtoRequest, date);
        assertEquals(calculateDtoResponse.getSumOfChillDaysMoney(), BigDecimal.valueOf(47781.58));
    }
}
