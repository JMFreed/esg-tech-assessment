package com.jfreed.kata;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorTests
{
    StringCalculator stringCalculator = StringCalculator.getInstance();

    @ParameterizedTest
    @MethodSource("step1TestCases")
    public void step1Tests(String input, int expected)
    {
        assertEquals(stringCalculator.add(input), expected);
    }

    static Stream<Arguments> step1TestCases() {
        return Stream.of(
                Arguments.of("", 0),
                Arguments.of("1", 1),
                Arguments.of("1,2", 3)
        );
    }
}
