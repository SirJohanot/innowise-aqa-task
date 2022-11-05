package com.innowise.regextesting.extractor;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DigitsNotFollowedWithPlusExtractorTest {

    private static final String TEXT_ONE = "86427654-";
    private static final List<String> EXPECTED_TEXT_ONE_MATCHES = Arrays.asList("8", "6", "4", "2", "7", "6", "5", "4");

    private static final String TEXT_TWO = "34";
    private static final List<String> EXPECTED_TEXT_TWO_MATCHES = Arrays.asList("3", "4");

    private static final String INVALID_TEXT_FOLLOWED_WITH_PLUS = "4+";
    private static final List<String> EXPECTED_INVALID_TEXT_FOLLOWED_WITH_PLUS_MATCHES = Collections.emptyList();

    private static final String INVALID_TEXT_NO_NUMBER = "-";
    private static final List<String> EXPECTED_INVALID_TEXT_NO_NUMBER_MATCHES = Collections.emptyList();

    private static final String SAMPLE_TEXT_BLOCK = """
            4+2
            3*6-7+
            6*2+(5-3)*3-8
            (3+4)+7*2-1-9
            5-2+4*(8-(5+1))+9
            (8-1+3)*6-((3+7)*2)
            """;
    private static final List<String> EXPECTED_TEXT_BLOCK_MATCHES = Arrays.asList("2", "3", "6", "6", "5", "3", "3", "8", "4", "7", "2", "1", "9", "5", "4", "8", "1", "9", "8", "3", "6", "7", "2");

    private DigitsNotFollowedWithPlusExtractor extractor;

    @BeforeEach
    public void setup() {
        extractor = new DigitsNotFollowedWithPlusExtractor();
    }

    @AfterEach
    public void terDown() {
        extractor = null;
    }

    @Test
    public void testExtractFromStringShouldExtractTheDigitWhenStringContainsANonPlusSignAfterTheDigit() {
        //given
        //when
        List<String> actualMatches = extractor.extractFromString(TEXT_ONE);
        //then
        Assertions.assertEquals(EXPECTED_TEXT_ONE_MATCHES, actualMatches);
    }

    @Test
    public void testExtractFromStringShouldExtractDigitsWhenStringOnlyContainsDigits() {
        //given
        //when
        List<String> actualMatches = extractor.extractFromString(TEXT_TWO);
        //then
        Assertions.assertEquals(EXPECTED_TEXT_TWO_MATCHES, actualMatches);
    }

    @Test
    public void testExtractFromStringShouldNotExtractWhenThereIsAPlusSignAfterTheDigit() {
        //given
        //when
        List<String> actualMatches = extractor.extractFromString(INVALID_TEXT_FOLLOWED_WITH_PLUS);
        //then
        Assertions.assertEquals(EXPECTED_INVALID_TEXT_FOLLOWED_WITH_PLUS_MATCHES, actualMatches);
    }

    @Test
    public void testExtractFromStringShouldNotExtractWhenStringDoesNotContainADigit() {
        //given
        //when
        List<String> actualMatches = extractor.extractFromString(INVALID_TEXT_NO_NUMBER);
        //then
        Assertions.assertEquals(EXPECTED_INVALID_TEXT_NO_NUMBER_MATCHES, actualMatches);
    }

    @Test
    public void testExtractFromStringShouldExtractAllValidDigitsWhenTextContainsManySymbols() {
        //given
        //when
        List<String> actualMatches = extractor.extractFromString(SAMPLE_TEXT_BLOCK);
        //then
        Assertions.assertEquals(EXPECTED_TEXT_BLOCK_MATCHES, actualMatches);
    }

}
