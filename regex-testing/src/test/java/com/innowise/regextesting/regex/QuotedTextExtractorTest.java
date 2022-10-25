package com.innowise.regextesting.regex;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuotedTextExtractorTest {

    private static final String TEXT_ONE = "\"Lorem ipsum\"";
    private static final List<String> EXPECTED_TEXT_ONE_MATCHES = List.of("Lorem ipsum");

    private static final String TEXT_TWO = "\"762574627846574625\"lorem";
    private static final List<String> EXPECTED_TEXT_TWO_MATCHES = List.of("762574627846574625");

    private static final String INVALID_TEXT_SINGLE_QUOTATION = "\"lorem ipsum";
    private static final List<String> EXPECTED_INVALID_TEXT_SINGLE_QUOTATION_MATCHES = Collections.emptyList();

    private static final String INVALID_TEXT_EMPTY = "\"\"";
    private static final List<String> EXPECTED_INVALID_TEXT_EMPTY_MATCHES = Collections.emptyList();

    private static final String SAMPLE_TEXT_BLOCK = """
            "It all started on one line
            and ended on another"
            ""
            another quotation"
            """;
    private static final List<String> EXPECTED_TEXT_BLOCK_MATCHES = Arrays.asList("It all started on one line\nand ended on another", "\n", "\nanother quotation");

    private QuotedTextExtractor extractor;

    @BeforeEach
    public void setup() {
        extractor = new QuotedTextExtractor();
    }

    @AfterEach
    public void terDown() {
        extractor = null;
    }

    @Test
    public void testExtractFromStringShouldExtractTextWhenTheStringOnlyHasTheQuotedText() {
        //given
        //when
        List<String> actualMatches = extractor.extractFromString(TEXT_ONE);
        //then
        Assertions.assertEquals(EXPECTED_TEXT_ONE_MATCHES, actualMatches);
    }

    @Test
    public void testExtractFromStringShouldExtractTextWhenTheStringHasInvalidQuotes() {
        //given
        //when
        List<String> actualMatches = extractor.extractFromString(TEXT_TWO);
        //then
        Assertions.assertEquals(EXPECTED_TEXT_TWO_MATCHES, actualMatches);
    }

    @Test
    public void testExtractFromStringShouldNotExtractWhenTheStringOnlyContainsAnInvalidQuote() {
        //given
        //when
        List<String> actualMatches = extractor.extractFromString(INVALID_TEXT_SINGLE_QUOTATION);
        //then
        Assertions.assertEquals(EXPECTED_INVALID_TEXT_SINGLE_QUOTATION_MATCHES, actualMatches);
    }

    @Test
    public void testExtractFromStringShouldNotExtractWhenTheStringHasEmptyQuotations() {
        //given
        //when
        List<String> actualMatches = extractor.extractFromString(INVALID_TEXT_EMPTY);
        //then
        Assertions.assertEquals(EXPECTED_INVALID_TEXT_EMPTY_MATCHES, actualMatches);
    }

    @Test
    public void testExtractFromStringShouldExtractAllValidQuotedTextWhenTextContainsManySymbols() {
        //given
        //when
        List<String> actualMatches = extractor.extractFromString(SAMPLE_TEXT_BLOCK);
        //then
        Assertions.assertEquals(EXPECTED_TEXT_BLOCK_MATCHES, actualMatches);
    }
}
