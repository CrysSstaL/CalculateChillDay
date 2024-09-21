package org.calc.calculator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.calc.calculator.dto.requests.CalculateDtoRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class CalculateControllerTests {

    @Autowired
    private MockMvc mockMvc;

    private Date date;
    private CalculateDtoRequest calculateDtoRequest;

    @Test
    public void CalculateControllerTests_withBadStartData() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        calculateDtoRequest = new CalculateDtoRequest(new BigDecimal(-1000), 22);
        String jsonRequest = objectMapper.writeValueAsString(calculateDtoRequest);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void CalculateControllerTests_withTrueStartData() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        calculateDtoRequest = new CalculateDtoRequest(new BigDecimal(1000), 22);
        String jsonRequest = objectMapper.writeValueAsString(calculateDtoRequest);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
        ).andExpect(status().isOk());
    }

    @Test
    public void CalculateControllerTests_withTrueStartDate() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        calculateDtoRequest = new CalculateDtoRequest(new BigDecimal(1000), 22);
        String date = "2024-01-01";
        String jsonRequest = objectMapper.writeValueAsString(calculateDtoRequest);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
                .param("date", date)
        ).andExpect(status().isOk());
    }

    @Test
    public void CalculateControllerTests_withBadStartDate() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        calculateDtoRequest = new CalculateDtoRequest(new BigDecimal(1000), 22);
        String date = "2024-11-235";
        String jsonRequest = objectMapper.writeValueAsString(calculateDtoRequest);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
                .param("date", date)
        ).andExpect(status().isBadRequest());
    }


}
