package com.innowise.regextesting.regex;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ValidExpressionExtractorTest {

    private static final String TEXT_ONE = "(2+2)";
    private static final List<String> EXPECTED_TEXT_ONE_MATCHES = List.of("2+2");

    private static final String TEXT_TWO = "(1*8)9)";
    private static final List<String> EXPECTED_TEXT_TWO_MATCHES = List.of("1*8");

    private static final String INVALID_TEXT_SINGLE_QUOTATION = "(lorem ipsum";
    private static final List<String> EXPECTED_INVALID_TEXT_SINGLE_QUOTATION_MATCHES = Collections.emptyList();

    private static final String INVALID_TEXT_EMPTY = "()";
    private static final List<String> EXPECTED_INVALID_TEXT_EMPTY_MATCHES = Collections.emptyList();

    private static final String SAMPLE_TEXT_BLOCK = """
            (1+2*3(1+2))
            (3+5))
            ((1+2+3)+4)
            """;
    private static final List<String> EXPECTED_TEXT_BLOCK_MATCHES = Arrays.asList("1+2*3(1+2)", "(1+2+3)+4");

    private ValidExpressionExtractor extractor;

    @BeforeEach
    public void setup() {
        extractor = new ValidExpressionExtractor();
    }

    @AfterEach
    public void terDown() {
        extractor = null;
    }

    @Test
    public void testExtractFromStringShouldExtractExpressionsWhenTheStringOnlyHasTheValidExpression() {
        //given
        //when
        List<String> actualMatches = extractor.extractFromString(TEXT_ONE);
        //then
        Assertions.assertEquals(EXPECTED_TEXT_ONE_MATCHES, actualMatches);
    }

    @Test
    public void testExtractFromStringShouldExtractExpressionsWhenTheStringHasInvalidBrackets() {
        //given
        //when
        List<String> actualMatches = extractor.extractFromString(TEXT_TWO);
        //then
        Assertions.assertEquals(EXPECTED_TEXT_TWO_MATCHES, actualMatches);
    }

    @Test
    public void testExtractFromStringShouldNotExtractWhenTheStringOnlyContainsAnInvalidExpression() {
        //given
        //when
        List<String> actualMatches = extractor.extractFromString(INVALID_TEXT_SINGLE_QUOTATION);
        //then
        Assertions.assertEquals(EXPECTED_INVALID_TEXT_SINGLE_QUOTATION_MATCHES, actualMatches);
    }

    @Test
    public void testExtractFromStringShouldNotExtractWhenTheStringHasAnEmptyExpression() {
        //given
        //when
        List<String> actualMatches = extractor.extractFromString(INVALID_TEXT_EMPTY);
        //then
        Assertions.assertEquals(EXPECTED_INVALID_TEXT_EMPTY_MATCHES, actualMatches);
    }

    @Test
    public void testExtractFromStringShouldExtractAllValidExpressionsWhenTextContainsManySymbols() {
        //given
        //when
        List<String> actualMatches = extractor.extractFromString(SAMPLE_TEXT_BLOCK);
        //then
        Assertions.assertEquals(EXPECTED_TEXT_BLOCK_MATCHES, actualMatches);
    }

}
