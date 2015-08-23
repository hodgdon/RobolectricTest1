package com.example.dhodgdon.robolectrictest1;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class CalculatorModelTest {
    @SuppressWarnings("ConstantConditions")
    @Test
    public void testComputeForExceptions() throws Exception {
        boolean error = false;
        try {
            CalculatorModel.compute(null,CalculatorModel.OPERATOR_PLUS,null);
            error = true;
        } catch(NullPointerException ignored) {}
        try {
            CalculatorModel.compute(null,CalculatorModel.OPERATOR_MINUS,null);
            error = true;
        } catch(NullPointerException ignored) {}
        assertFalse(error);
    }

    @Test
    public void testCompute() throws Exception {
        assertEquals(new BigInteger("0"), CalculatorModel.compute(new BigInteger("0"),CalculatorModel.OPERATOR_PLUS, new BigInteger("0")));
        assertEquals(new BigInteger("1"), CalculatorModel.compute(new BigInteger("1"),CalculatorModel.OPERATOR_PLUS, new BigInteger("0")));
        assertEquals(new BigInteger("1"), CalculatorModel.compute(new BigInteger("0"),CalculatorModel.OPERATOR_PLUS, new BigInteger("1")));
        assertEquals(new BigInteger("2"), CalculatorModel.compute(new BigInteger("1"),CalculatorModel.OPERATOR_PLUS, new BigInteger("1")));
        assertEquals(new BigInteger("100"), CalculatorModel.compute(new BigInteger("49"),CalculatorModel.OPERATOR_PLUS, new BigInteger("51")));


        assertEquals(new BigInteger("0"), CalculatorModel.compute(new BigInteger("1"),CalculatorModel.OPERATOR_PLUS, new BigInteger("-1")));
        assertEquals(new BigInteger("1"), CalculatorModel.compute(new BigInteger("2"),CalculatorModel.OPERATOR_PLUS, new BigInteger("-1")));
        assertEquals(new BigInteger("1"), CalculatorModel.compute(new BigInteger("-1"),CalculatorModel.OPERATOR_PLUS, new BigInteger("2")));
        assertEquals(new BigInteger("2"), CalculatorModel.compute(new BigInteger("-1"),CalculatorModel.OPERATOR_PLUS, new BigInteger("3")));
        assertEquals(new BigInteger("100"), CalculatorModel.compute(new BigInteger("-200"),CalculatorModel.OPERATOR_PLUS, new BigInteger("300")));

        assertEquals(new BigInteger("0"), CalculatorModel.compute(new BigInteger("12"),CalculatorModel.OPERATOR_MINUS, new BigInteger("12")));
        assertEquals(new BigInteger("1"), CalculatorModel.compute(new BigInteger("13"),CalculatorModel.OPERATOR_MINUS, new BigInteger("12")));
        assertEquals(new BigInteger("1"), CalculatorModel.compute(new BigInteger("100"),CalculatorModel.OPERATOR_MINUS, new BigInteger("99")));
        assertEquals(new BigInteger("2"), CalculatorModel.compute(new BigInteger("3"),CalculatorModel.OPERATOR_MINUS, new BigInteger("1")));
        assertEquals(new BigInteger("100"), CalculatorModel.compute(new BigInteger("103"),CalculatorModel.OPERATOR_MINUS, new BigInteger("3")));


        assertEquals(new BigInteger("2"), CalculatorModel.compute(new BigInteger("1"),CalculatorModel.OPERATOR_MINUS, new BigInteger("-1")));
        assertEquals(new BigInteger("3"), CalculatorModel.compute(new BigInteger("2"),CalculatorModel.OPERATOR_MINUS, new BigInteger("-1")));
        assertEquals(new BigInteger("-3"), CalculatorModel.compute(new BigInteger("-1"),CalculatorModel.OPERATOR_MINUS, new BigInteger("2")));
        assertEquals(new BigInteger("-4"), CalculatorModel.compute(new BigInteger("-1"),CalculatorModel.OPERATOR_MINUS, new BigInteger("3")));
        assertEquals(new BigInteger("-500"), CalculatorModel.compute(new BigInteger("-200"),CalculatorModel.OPERATOR_MINUS, new BigInteger("300")));

    }
}