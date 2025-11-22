package com.jfreed.kata;

public class StringCalculator
{
    private StringCalculator()
    {
    }

    public static StringCalculator getInstance()
    {
        return SingletonHelper.INSTANCE;
    }

    public int add(String input)
    {
        return 0;
    }

    private static class SingletonHelper
    {
        private static final StringCalculator INSTANCE = new StringCalculator();
    }
}
