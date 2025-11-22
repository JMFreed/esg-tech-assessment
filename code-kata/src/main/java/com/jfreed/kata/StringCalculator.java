package com.jfreed.kata;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator
{
    private StringCalculator()
    {
    }

    public static StringCalculator getInstance()
    {
        return SingletonHelper.INSTANCE;
    }

    public int add(String input) throws NegativeNumberException
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
            String numberSequence = input.substring(index + 1);

            String delimiter = specifiedDelimiter.replaceFirst("^//", "");
            String escapedDelimiter = Pattern.quote(delimiter);

            List<Integer> numbers = numberList(numberSequence, escapedDelimiter);
            checkForNegativeNumbers(numbers);
            return addInternal(numbers);
        }

        List<Integer> numbers = numberList(input, defaultDelimiter);
        checkForNegativeNumbers(numbers);
        return addInternal(numbers);
    }

    private int addInternal(List<Integer> numbers)
    {
        return numbers.stream()
                .filter(n -> n <= 1000)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private List<Integer> numberList(String input, String delimiter)
    {
        return Arrays.stream(input.split(delimiter))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    private void checkForNegativeNumbers(List<Integer> numbers) throws NegativeNumberException
    {
        List<Integer> negatives = numbers.stream().filter(i -> i < 0).toList();
        if (!negatives.isEmpty())
        {
            List<String> strNegatives = negatives.stream().map(String::valueOf).toList();
            String message = "Negatives not allowed: " + String.join(",", strNegatives);
            throw new NegativeNumberException(message);
        }
    }

    private static class SingletonHelper
    {
        private static final StringCalculator INSTANCE = new StringCalculator();
    }
}
