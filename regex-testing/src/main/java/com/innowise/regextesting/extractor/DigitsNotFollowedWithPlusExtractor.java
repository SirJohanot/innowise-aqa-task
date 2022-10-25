package com.innowise.regextesting.extractor;

public class DigitsNotFollowedWithPlusExtractor extends StringRegexExtractor {

    private static final String NO_PLUS_AFTER_DIGIT_REGEX = "\\d(?!\\+)";

    public DigitsNotFollowedWithPlusExtractor() {
        super();
    }

    @Override
    protected String getRegex() {
        return NO_PLUS_AFTER_DIGIT_REGEX;
    }
}
