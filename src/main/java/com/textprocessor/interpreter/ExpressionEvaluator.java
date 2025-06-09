package com.textprocessor.interpreter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionEvaluator {
    private static final Pattern EXPRESSION_PATTERN = Pattern.compile("\\(([^()]+)\\)");
    private static final Pattern NUMBER_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static String evaluateExpressions(String text) {
        Matcher matcher = EXPRESSION_PATTERN.matcher(text);
        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            String expression = matcher.group(1);
            double value = evaluateExpression(expression);
            matcher.appendReplacement(result, String.valueOf(value));
        }
        matcher.appendTail(result);

        return result.toString();
    }

    private static double evaluateExpression(String expression) {
        // Remove all whitespace
        expression = expression.replaceAll("\\s+", "");

        // Handle basic arithmetic operations
        if (expression.contains("+")) {
            String[] parts = expression.split("\\+");
            return evaluateExpression(parts[0]) + evaluateExpression(parts[1]);
        }
        if (expression.contains("-")) {
            String[] parts = expression.split("-");
            return evaluateExpression(parts[0]) - evaluateExpression(parts[1]);
        }
        if (expression.contains("*")) {
            String[] parts = expression.split("\\*");
            return evaluateExpression(parts[0]) * evaluateExpression(parts[1]);
        }
        if (expression.contains("/")) {
            String[] parts = expression.split("/");
            return evaluateExpression(parts[0]) / evaluateExpression(parts[1]);
        }

        // If it's just a number, return it
        if (NUMBER_PATTERN.matcher(expression).matches()) {
            return Double.parseDouble(expression);
        }

        return 0;
    }
} 