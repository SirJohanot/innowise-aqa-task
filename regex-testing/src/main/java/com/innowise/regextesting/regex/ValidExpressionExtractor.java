package com.innowise.regextesting.regex;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidExpressionExtractor implements StringExtractor {

    private static final String BRACKET_EXPRESSION_REGEX = "(?<=\\().+(?=\\))";

    @Override
    public List<String> extractFromString(String text) {
        Pattern pattern = Pattern.compile(BRACKET_EXPRESSION_REGEX);
        Matcher matcher = pattern.matcher(text);
        return matcher.results()
                .map(MatchResult::group)
                .toList()
                .stream()
                .filter(ValidExpressionExtractor::expressionIsValid)
                .toList();
    }

    private static boolean expressionIsValid(String expression) {
        int openedBrackets = 0;
        int closedBrackets = 0;
        for (char c : expression.toCharArray()) {
            if (c == '(') {
                openedBrackets++;
            } else if (c == ')') {
                closedBrackets++;
            }
            if (closedBrackets > openedBrackets) {
                break;
            }
        }
        return closedBrackets == openedBrackets;
    }
}
