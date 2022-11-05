package com.patiun.regextesting.extractor;

public class FloatNumberExtractor extends StringRegexExtractor {

    private static final String FLOAT_NUMBER_REGEX = "([1-9]+([ ,]?\\d)+|0)\\.\\d+";

    public FloatNumberExtractor() {
        super();
    }

    @Override
    protected String getRegex() {
        return FLOAT_NUMBER_REGEX;
    }
}
