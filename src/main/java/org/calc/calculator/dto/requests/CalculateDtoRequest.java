package org.calc.calculator.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
@Setter
@Getter
@AllArgsConstructor
public class CalculateDtoRequest {

    @Min(0)
    @NotNull
    private BigDecimal avgSalary;

    @Min(0)
    @NotNull
    private Integer countOfChillDay;
}
