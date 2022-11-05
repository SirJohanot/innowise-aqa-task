package com.patiun.regextesting.extractor;

public class QuotedTextExtractor extends StringRegexExtractor {

    private static final String QUOTED_TEXT_REGEX = "(?<=\")([^\"])+?(?=\")";

    public QuotedTextExtractor() {
        super();
    }

    @Override
    protected String getRegex() {
        return QUOTED_TEXT_REGEX;
    }
}
