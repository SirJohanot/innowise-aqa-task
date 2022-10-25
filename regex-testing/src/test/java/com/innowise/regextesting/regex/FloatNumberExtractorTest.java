package com.innowise.regextesting.regex;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FloatNumberExtractorTest {

    private static final String TEXT_ONE = "43,000.375";
    private static final List<String> EXPECTED_TEXT_ONE_MATCHES = List.of("43,000.375");

    private static final String TEXT_TWO = "0.5";
    private static final List<String> EXPECTED_TEXT_TWO_MATCHES = List.of("0.5");

    private static final String INVALID_TEXT_INTEGER = "40";
    private static final List<String> EXPECTED_INVALID_TEXT_INTEGER_MATCHES = Collections.emptyList();

    private static final String INVALID_TEXT_SPACE_AFTER_DELIMITER = "3. 675";
    private static final List<String> EXPECTED_INVALID_TEXT_SPACE_AFTER_DELIMITER_MATCHES = Collections.emptyList();

    private static final String SAMPLE_TEXT_BLOCK = """
            74 500.841
            63 .068
            18.381
            049.092
            """;
    private static final List<String> EXPECTED_TEXT_BLOCK_MATCHES = Arrays.asList("74 500.841", "18.381", "49.092");

    private FloatNumberExtractor extractor;

    @BeforeEach
    public void setup() {
        extractor = new FloatNumberExtractor();
    }

    @AfterEach
    public void terDown() {
        extractor = null;
    }

    @Test
    public void testExtractFromStringShouldExtractTheFloatWhenTheNumberHasASpaceDelimiter() {
        //given
        //when
        List<String> actualMatches = extractor.extractFromString(TEXT_ONE);
        //then
        Assertions.assertEquals(EXPECTED_TEXT_ONE_MATCHES, actualMatches);
    }

    @Test
    public void testExtractFromStringShouldExtractTheFloatWhenTheNumberIsLessThanOne() {
        //given
        //when
        List<String> actualMatches = extractor.extractFromString(TEXT_TWO);
        //then
        Assertions.assertEquals(EXPECTED_TEXT_TWO_MATCHES, actualMatches);
    }

    @Test
    public void testExtractFromStringShouldNotExtractWhenTheStringOnlyContainsAnInteger() {
        //given
        //when
        List<String> actualMatches = extractor.extractFromString(INVALID_TEXT_INTEGER);
        //then
        Assertions.assertEquals(EXPECTED_INVALID_TEXT_INTEGER_MATCHES, actualMatches);
    }

    @Test
    public void testExtractFromStringShouldNotExtractWhenTheFloatPointIsNotImmediatelyFollowedByDigits() {
        //given
        //when
        List<String> actualMatches = extractor.extractFromString(INVALID_TEXT_SPACE_AFTER_DELIMITER);
        //then
        Assertions.assertEquals(EXPECTED_INVALID_TEXT_SPACE_AFTER_DELIMITER_MATCHES, actualMatches);
    }

    @Test
    public void testExtractFromStringShouldExtractAllValidFloatsWhenTextContainsManySymbols() {
        //given
        //when
        List<String> actualMatches = extractor.extractFromString(SAMPLE_TEXT_BLOCK);
        //then
        Assertions.assertEquals(EXPECTED_TEXT_BLOCK_MATCHES, actualMatches);
    }

}
