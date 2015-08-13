package com.agualis.jbrains;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CalculatorShould {

    private Calculator calculator;

    @Test
    public void
    add_two_numbers() {
        calculator = new Calculator();
        assertThat(calculator.add(2, 3), is(5));
    }
}