package com.innowise.regextesting.regex;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class StringRegexExtractor implements StringExtractor {

    public StringRegexExtractor() {
    }

    @Override
    public List<String> extractFromString(String text) {
        Pattern pattern = Pattern.compile(getRegex());
        Matcher matcher = pattern.matcher(text);
        return matcher.results()
                .map(MatchResult::group)
                .toList();
    }

    protected abstract String getRegex();
}
