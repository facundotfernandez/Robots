package org.example.modelo.utilidades;

public class StringUtil {
    public static String toProper(String input) {

        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder result = new StringBuilder();
        result.append(Character.toUpperCase(input.charAt(0)));

        for (int i = 1; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            char previousChar = input.charAt(i - 1);

            result.append((previousChar == ' ') ? Character.toUpperCase(currentChar) : Character.toLowerCase(currentChar));
        }

        return result.toString();
    }
}
