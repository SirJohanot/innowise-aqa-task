package com.innowise.regextesting.regex;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TimeExtractorTest {

    private static final String TEXT_ONE = "18:45";
    private static final List<String> EXPECTED_TEXT_ONE_MATCHES = List.of("18:45");

    private static final String TEXT_TWO = "00:00";
    private static final List<String> EXPECTED_TEXT_TWO_MATCHES = List.of("00:00");

    private static final String INVALID_TEXT_IMPOSSIBLE_TIME = "25:15";
    private static final List<String> EXPECTED_INVALID_TEXT_IMPOSSIBLE_TIME_MATCHES = Collections.emptyList();

    private static final String INVALID_TEXT_TOO_SHORT = "6:3";
    private static final List<String> EXPECTED_INVALID_TEXT_TOO_SHORT_MATCHES = Collections.emptyList();

    private static final String SAMPLE_TEXT_BLOCK = """
            Breakfast at 09:00
            37:98 is invalid time
            It is 14:29
            You have to respect the time format 6:15
            """;
    private static final List<String> EXPECTED_TEXT_BLOCK_MATCHES = Arrays.asList("09:00", "14:29");

    private TimeExtractor extractor;

    @BeforeEach
    public void setup() {
        extractor = new TimeExtractor();
    }

    @AfterEach
    public void terDown() {
        extractor = null;
    }

    @Test
    public void testExtractFromStringShouldExtractTimeWhenStringOnlyContainsValidTime() {
        //given
        //when
        List<String> actualMatches = extractor.extractFromString(TEXT_ONE);
        //then
        Assertions.assertEquals(EXPECTED_TEXT_ONE_MATCHES, actualMatches);
    }

    @Test
    public void testExtractFromStringShouldExtractTimeWhenStringContainsMidnightTime() {
        //given
        //when
        List<String> actualMatches = extractor.extractFromString(TEXT_TWO);
        //then
        Assertions.assertEquals(EXPECTED_TEXT_TWO_MATCHES, actualMatches);
    }

    @Test
    public void testExtractFromStringShouldNotExtractWhenTheStringContainsImpossibleTime() {
        //given
        //when
        List<String> actualMatches = extractor.extractFromString(INVALID_TEXT_IMPOSSIBLE_TIME);
        //then
        Assertions.assertEquals(EXPECTED_INVALID_TEXT_IMPOSSIBLE_TIME_MATCHES, actualMatches);
    }

    @Test
    public void testExtractFromStringShouldNotExtractWhenTimeIsTooShort() {
        //given
        //when
        List<String> actualMatches = extractor.extractFromString(INVALID_TEXT_TOO_SHORT);
        //then
        Assertions.assertEquals(EXPECTED_INVALID_TEXT_TOO_SHORT_MATCHES, actualMatches);
    }

    @Test
    public void testExtractFromStringShouldExtractAllValidMatchesWhenTextContainsManySymbols() {
        //given
        //when
        List<String> actualMatches = extractor.extractFromString(SAMPLE_TEXT_BLOCK);
        //then
        Assertions.assertEquals(EXPECTED_TEXT_BLOCK_MATCHES, actualMatches);
    }
}
