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

    @ParameterizedTest
    @MethodSource("step2TestCases")
    public void step2Tests(String input, int expected)
    {
        assertEquals(stringCalculator.add(input), expected);
    }

    @ParameterizedTest
    @MethodSource("step3TestCases")
    public void step3Tests(String input, int expected)
    {
        assertEquals(stringCalculator.add(input), expected);
    }

    @ParameterizedTest
    @MethodSource("step4TestCases")
    public void step4Tests(String input, int expected)
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

    static Stream<Arguments> step2TestCases() {
        return Stream.of(
                Arguments.of("5,6,7,8", 26),
                Arguments.of("1,2,3,4,5,6,7,8,9", 45),
                Arguments.of("5,9,21,5,20,40", 100)
        );
    }

    static Stream<Arguments> step3TestCases() {
        return Stream.of(
                Arguments.of("1\n2,3", 6),
                Arguments.of("2\n4\n6", 12)
        );
    }

    static Stream<Arguments> step4TestCases() {
        return Stream.of(
                Arguments.of("//;\n1;2", 3),
                Arguments.of("//$$\n3$$5$$7", 15)
        );
    }
}
