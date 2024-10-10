package com.emazon.stock_api_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StockApiServiceCategoryTest {
    Long number;
    @Test
    void contextLoads() {
        number = 1L;
    }

    @Test
    void categoryTest(){
        number = 2L;
        assertEquals(2,number);
    }
}
