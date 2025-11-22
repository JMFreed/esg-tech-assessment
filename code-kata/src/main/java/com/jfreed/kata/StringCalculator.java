package com.jfreed.kata;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        String defaultDelimiter = "[,\\n]";

        int index = input.indexOf('\n');
        if (input.startsWith("//") && index != -1)
        {
            String specifiedDelimiter = input.substring(0, index);
            String numbers = input.substring(index + 1);
            String delimiter = specifiedDelimiter.replaceFirst("^//", "");
            String escapedDelimiter = Pattern.quote(delimiter);
            return Arrays.stream(numbers.split(escapedDelimiter))
                    .map(Integer::valueOf)
                    .mapToInt(Integer::intValue)
                    .sum();
        }

        return Arrays.stream(input.split(defaultDelimiter))
                .map(Integer::valueOf)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private static class SingletonHelper
    {
        private static final StringCalculator INSTANCE = new StringCalculator();
    }
}
