package com.tryhard.maxnumber.services;

import com.tryhard.maxnumber.model.MaxNumberResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MaxNumberServiceImplTest {

    @Autowired
    private MaxNumberService maxNumberService;

    @Test
    void getMaxNumberTest() throws IOException {
        MaxNumberResponse maxNumberResponse = maxNumberService.getMaxNumber(1, "src/main/resources/test.xlsx");
        assertEquals(maxNumberResponse.getResult(), "9");
    }
}
