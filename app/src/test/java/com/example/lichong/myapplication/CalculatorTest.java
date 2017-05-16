package com.example.lichong.myapplication;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by lichong on 2017/5/14.
 */
public class CalculatorTest {

    private static Calculator calculator=new Calculator();
    @Before
    public void setUp() throws Exception {
        calculator.clear();
    }

    @Test
    public void add() throws Exception {
            calculator.add(2);
            calculator.add(3);
    assertEquals(5,calculator.getResult());
    }

    @Test
    public void substract() throws Exception {
        calculator.add(10);
        calculator.substract(2);
        assertEquals(8,calculator.getResult());
    }

    @Ignore("Multiply() not yet implemented")
    @Test
    public void multiply() throws Exception {

    }

    @Test
    public void divide() throws Exception {
        calculator.add(8);
        calculator.divide(2);
        assertEquals(4, calculator.getResult());
    }

    @Test
    public void square() throws Exception {

    }

    @Test(timeout = 1000)
    public void squareRoot() throws Exception {
        calculator.squareRoot(4);
        assertEquals(2, calculator.getResult());
    }

    @Test
    public void clear() throws Exception {

    }

    @Test
    public void getResult() throws Exception {

    }
    @Test(expected = ArithmeticException.class)
    public void divideByZero() {
        calculator.divide(0);
    }

}