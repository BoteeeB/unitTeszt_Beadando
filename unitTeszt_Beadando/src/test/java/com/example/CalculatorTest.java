package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CalculatorTest {

    private Calculator calculator;
    private Database mockDatabase;

    @BeforeEach
    void setUp() {
        mockDatabase = mock(Database.class);
        calculator = new Calculator(mockDatabase);
    }

    @Test
    void testAdd() {
        int result = calculator.add(2, 3);
        assertEquals(5, result);

        verify(mockDatabase).saveResult(5);
    }

    @Test
    void testSubtract() {
        int result = calculator.subtract(5, 3);
        assertEquals(2, result);
        verify(mockDatabase).saveResult(2);
    }

    @Test
    void testMultiply() {
        assertEquals(6, calculator.multiply(2, 3));
    }

    @Test
    void testDivide() {
        assertEquals(2, calculator.divide(6, 3));
    }

    @Test
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(1, 0));
    }

    @Test
    void testAddBoundary() {
        assertEquals(0, calculator.add(0, 0));
        assertEquals(100, calculator.add(50, 50));
        assertEquals(101, calculator.add(100, 1));
    }

    @Test
    void testSubtractBoundary() {
        assertEquals(0, calculator.subtract(100, 100));
        assertEquals(-1, calculator.subtract(0, 1));
    }

    @Test
    void testMultiplyBoundary() {
        assertEquals(0, calculator.multiply(0, 10));
        assertEquals(100, calculator.multiply(10, 10));
    }
}
