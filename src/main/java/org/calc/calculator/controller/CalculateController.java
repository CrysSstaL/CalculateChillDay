package org.calc.calculator.controller;

import lombok.AllArgsConstructor;
import org.calc.calculator.dto.requests.CalculateDtoRequest;
import org.calc.calculator.service.CalculateService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;

@RestController("/")
@AllArgsConstructor
public class CalculateController {
    private final CalculateService calculateService;

    @GetMapping("calculate")
    public ResponseEntity<?> getSum(@RequestBody @Valid CalculateDtoRequest calculateDtoRequest,
                                    @RequestParam(value = "date", required = false)
                                    @DateTimeFormat(pattern = "yyyy-MM-dd") Date date
                                    ) throws IOException {
        return ResponseEntity.ok(calculateService.calculate(calculateDtoRequest, date));
    }

}
