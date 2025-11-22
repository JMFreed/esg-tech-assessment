package com.jfreed.kata;

import java.util.Arrays;

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
        if (input.isEmpty())
        {
            return 0;
        }

        return Arrays.stream(input.split("[,\\n]"))
                .map(Integer::valueOf)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private static class SingletonHelper
    {
        private static final StringCalculator INSTANCE = new StringCalculator();
    }
}
