package com.innowise.regextesting.extractor;

public class TimeExtractor extends StringRegexExtractor {

    private static final String TIME_REGEX = "([01]\\d|2[0-3]):[0-5]\\d";

    public TimeExtractor() {
        super();
    }

    @Override
    protected String getRegex() {
        return TIME_REGEX;
    }
}
