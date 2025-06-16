package com.example.demo.controller;

import com.example.demo.service.MathService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MathControllerTest {

    @Test
    void testAddEndpoint() {
        MathService mathService = mock(MathService.class);
        when(mathService.add(2, 3)).thenReturn(5);
        
        MathController controller = new MathController(mathService);
        String result = controller.add(2, 3);
        
        assertEquals("Result: 5", result);
    }

    @Test
    void testSubtractEndpoint() {
        MathService mathService = mock(MathService.class);
        when(mathService.subtract(5, 2)).thenReturn(3);
        
        MathController controller = new MathController(mathService);
        String result = controller.subtract(5, 2);
        
        assertEquals("Result: 3", result);
    }
}