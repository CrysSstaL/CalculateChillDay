package org.calc.calculator.service;

import org.calc.calculator.dto.requests.CalculateDtoRequest;
import org.calc.calculator.dto.responses.CalculateDtoResponse;

import java.io.IOException;
import java.util.Date;

public interface CalculateService {
    CalculateDtoResponse calculate(CalculateDtoRequest calculateDtoRequest, Date date) throws IOException;
}
