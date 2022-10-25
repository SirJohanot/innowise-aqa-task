package com.innowise.regextesting.regex;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlColorRegexTest {

    private static final String HTML_COLOR_REGEX = "#[\\dA-F]{6}";

    private static final String VALID_TEXT_ONE = "#96347B";
    private static final String VALID_TEXT_TWO = "#AAAAAA";
    private static final String INVALID_TEXT_TOO_SHORT = "#3275F";
    private static final String INVALID_TEXT_NON_HEX_CHARACTER = "#88888J";
    private static final String SAMPLE_TEXT_BLOCK = """
            tPxQUIwIwRIterO9yT3Z5jPbKVYNnob3
            WMq1Xr7#444444xvDEyeGiU8TM0tOZXXQzGuj7
            wtm#8736BDKvCF#FF8321X6NNwqkXUU7JH6QIGvDD2fS
            knJ50ZmEIWrpNLgosIXJdjEEaItGVArZ
            2aUKmhCMSRjpEv9GZYuY5pTrqqIupbAe
            """;
    private static final List<String> EXPECTED_TEXT_BLOCK_MATCHES = Arrays.asList("#444444", "#8736BD", "#FF8321");

    @Test
    public void testHtmlColorRegexShouldMatchWhenColorContainsNumbers() {
        //given
        //when
        boolean matched = Pattern.matches(HTML_COLOR_REGEX, VALID_TEXT_ONE);
        //then
        Assertions.assertTrue(matched);
    }

    @Test
    public void testHtmlColorRegexShouldMatchWhenColorContainsOnlyHexLetters() {
        //given
        //when
        boolean matched = Pattern.matches(HTML_COLOR_REGEX, VALID_TEXT_TWO);
        //then
        Assertions.assertTrue(matched);
    }

    @Test
    public void testHtmlColorRegexShouldNotMatchWhenColorIsShorterThanSixSymbols() {
        //given
        //when
        boolean matched = Pattern.matches(HTML_COLOR_REGEX, INVALID_TEXT_TOO_SHORT);
        //then
        Assertions.assertFalse(matched);
    }

    @Test
    public void testHtmlColorRegexShouldNotMatchWhenColorContainsNonHexCharacters() {
        //given
        //when
        boolean matched = Pattern.matches(HTML_COLOR_REGEX, INVALID_TEXT_NON_HEX_CHARACTER);
        //then
        Assertions.assertFalse(matched);
    }

    @Test
    public void testHtmlColorRegexShouldFindAllValidMatchesWhenTextContainsManySymbols() {
        //given
        Pattern pattern = Pattern.compile(HTML_COLOR_REGEX);
        Matcher matcher = pattern.matcher(SAMPLE_TEXT_BLOCK);
        //when
        List<String> actualMatches = matcher.results()
                .map(MatchResult::group)
                .toList();
        //then
        Assertions.assertEquals(EXPECTED_TEXT_BLOCK_MATCHES, actualMatches);
    }

}
