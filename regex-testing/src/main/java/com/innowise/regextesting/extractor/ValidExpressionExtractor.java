package com.innowise.regextesting.extractor;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidExpressionExtractor implements StringExtractor {

    private static final String BRACKET_EXPRESSION_EAGER_REGEX = "(?<=\\().+(?=\\))";

    @Override
    public List<String> extractFromString(String text) {
        Pattern eagerPattern = Pattern.compile(BRACKET_EXPRESSION_EAGER_REGEX);
        Matcher eagerMatcher = eagerPattern.matcher(text);
        return eagerMatcher.results()
                .map(MatchResult::group)
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
