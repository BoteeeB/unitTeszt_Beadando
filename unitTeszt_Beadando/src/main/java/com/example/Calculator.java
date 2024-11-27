package com.example;

public class Calculator {

    private Database database;

    public Calculator(Database database) {
        this.database = database;
    }

    public int add(int a, int b) {
        int result = a + b;
        database.saveResult(result);
        return result;
    }

    public int subtract(int a, int b) {
        int result = a - b;
        database.saveResult(result);
        return result;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public int divide(int a, int b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    }
}
