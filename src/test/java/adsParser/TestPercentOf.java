package adsParser;

import numericFunctions.PercentOf;
import org.junit.Assert;
import org.junit.Test;

public class TestPercentOf {

    @Test
    public void test_Init_Class() {
        Assert.assertTrue(new PercentOf() instanceof PercentOf);
    }

    @Test
    public void test_Two_Numbers_Zero() {
        Double number1 = 0.0;
        Double number2 = 0.0;

        Double result = new Double(PercentOf.TwoNumbers(number1, number2));

        Assert.assertEquals(result, new Double(0));
    }

    @Test
    public void test_Two_Numbers_50() {
        Double number1 = 25.0;
        Double number2 = 50.0;

        Double result = new Double(PercentOf.TwoNumbers(number1, number2));

        Assert.assertEquals(result, new Double(50));
    }

    @Test
    public void test_Two_Numbers_100() {
        Double number1 = 100.0;
        Double number2 = 50.0;

        Double result = new Double(PercentOf.TwoNumbers(number1, number2));

        Assert.assertEquals(result, new Double(100));
    }

}