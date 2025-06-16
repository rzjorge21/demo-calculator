package com.example.demo.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MathServiceTest {

    private final MathService mathService = new MathService();

    @Test
    void testAdd() {
        assertEquals(5, mathService.add(2, 3));
    }

    @Test
    void testSubtract() {
        assertEquals(1, mathService.subtract(3, 2));
    }
}