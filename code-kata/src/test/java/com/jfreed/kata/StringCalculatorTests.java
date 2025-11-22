package com.jfreed.kata;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringCalculatorTests
{
    StringCalculator stringCalculator = StringCalculator.getInstance();

    @ParameterizedTest
    @MethodSource("step1TestCases")
    public void step1Tests(String input, int expected) throws Exception
    {
        assertEquals(expected, stringCalculator.add(input));
    }

    @ParameterizedTest
    @MethodSource("step2TestCases")
    public void step2Tests(String input, int expected) throws Exception
    {
        assertEquals(expected, stringCalculator.add(input));
    }

    @ParameterizedTest
    @MethodSource("step3TestCases")
    public void step3Tests(String input, int expected) throws Exception
    {
        assertEquals(expected, stringCalculator.add(input));
    }

    @ParameterizedTest
    @MethodSource("step4TestCases")
    public void step4Tests(String input, int expected) throws Exception
    {
        assertEquals(expected, stringCalculator.add(input));
    }

    @ParameterizedTest
    @MethodSource("step5TestCases")
    public void step5Tests(String input, String expected) throws Exception
    {
        NegativeNumberException negativeNumberException =
                assertThrows(NegativeNumberException.class,
                        () -> stringCalculator.add(input));
        assertEquals("Negatives not allowed: " + expected,
                negativeNumberException.getMessage());
    }

    @ParameterizedTest
    @MethodSource("step6TestCases")
    public void step6Tests(String input, int expected) throws Exception
    {
        assertEquals(expected, stringCalculator.add(input));
    }

    static Stream<Arguments> step1TestCases()
    {
        return Stream.of(
                Arguments.of("", 0),
                Arguments.of("1", 1),
                Arguments.of("1,2", 3)
        );
    }

    static Stream<Arguments> step2TestCases()
    {
        return Stream.of(
                Arguments.of("5,6,7,8", 26),
                Arguments.of("1,2,3,4,5,6,7,8,9", 45),
                Arguments.of("5,9,21,5,20,40", 100)
        );
    }

    static Stream<Arguments> step3TestCases()
    {
        return Stream.of(
                Arguments.of("1\n2,3", 6),
                Arguments.of("2\n4\n6", 12)
        );
    }

    static Stream<Arguments> step4TestCases()
    {
        return Stream.of(
                Arguments.of("//;\n1;2", 3),
                Arguments.of("//$$\n3$$5$$7", 15)
        );
    }

    static Stream<Arguments> step5TestCases()
    {
        return Stream.of(
                Arguments.of("-1,2", "-1"),
                Arguments.of("2,-4,3,-5", "-4,-5")
        );
    }

    static Stream<Arguments> step6TestCases()
    {
        return Stream.of(
                Arguments.of("1000,3,4", 1007),
                Arguments.of("1001,2", 2),
                Arguments.of("1,2,3000,4000,5", 8)
        );
    }
}
