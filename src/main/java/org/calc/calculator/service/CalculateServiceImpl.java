package org.calc.calculator.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.calc.calculator.dto.requests.CalculateDtoRequest;
import org.calc.calculator.dto.responses.CalculateDtoResponse;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class CalculateServiceImpl implements CalculateService{

    @Override
    public CalculateDtoResponse calculate(CalculateDtoRequest calculateDtoRequest, Date date) throws IOException {
        if (date != null) {
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int i = 0;
            int countWorkDays = 0;
            while (i < calculateDtoRequest.getCountOfChillDay()) {
                HashMap<String, List<String>> dates = getResource("chillDays/consultant2024.json");
                if (!dates.get("holidays").contains(localDate.toString())
                        && !dates.get("preholidays").contains(localDate.toString())) {
                    countWorkDays++;
                }
                localDate = localDate.plusDays(1);
                i++;
            }
            calculateDtoRequest.setCountOfChillDay(countWorkDays);
        }
        CalculateDtoResponse calculateDtoResponse =
                new CalculateDtoResponse();
        calculateDtoResponse.setSumOfChillDaysMoney(
                calculateDtoRequest.getAvgSalary()
                        .divide(BigDecimal.valueOf(29.3), 2, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(calculateDtoRequest.getCountOfChillDay()))
        );
        return calculateDtoResponse;
    }

    public HashMap<String, List<String>> getResource(String resource) throws IOException {
        File file = new File(
                this.getClass().getClassLoader().getResource(resource).getFile()
        );
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(file, HashMap.class);
    }
}
