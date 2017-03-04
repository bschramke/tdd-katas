package com.github.bschramke.tddkatas;

import java.util.ArrayDeque;
import java.util.EmptyStackException;
import java.util.Queue;

public class RomanNumeralsConverter {
    public String fromArabic(final int arabic) {
        if(arabic < 0) throw new IllegalArgumentException();
        if(arabic == 0) return "N";

        final StringBuilder result = new StringBuilder();
        int remaining = arabic;

        for(RomanNumeral symbol : RomanNumeral.values()) {
            remaining = appendNumeral(result, remaining, symbol.weight(), symbol.name());
        }

        return result.toString();
    }

    public int toArabic(final String roman) {
        final String input = roman.toUpperCase();

        if(input.equals("N")) return 0;
        if(RomanNumeral.isSymbol(input)) return RomanNumeral.valueOf(input).weight();

        int result = 0;
        Queue<Character> charStack = stringToStack(roman);

        while(!charStack.isEmpty()) {
            result += parseSymbol(charStack).weight();
        }

        return result;
    }

    private RomanNumeral parseSymbol(final Queue<Character> charStack) {
        final StringBuilder builder = new StringBuilder(2);
        final char current = charStack.poll();
        final char next = stackPeek(charStack);

        builder.append(current);

        if(RomanNumeral.isSubstractionAllowed(current,next)) {
                builder.append(next);
                charStack.poll();
        }

        return RomanNumeral.valueOf(builder.toString());
    }

    private char stackPeek(Queue<Character> charStack) {
        char nextChar = 0;
        try {
            nextChar = charStack.peek();
        }catch (EmptyStackException | NullPointerException e) {
            //nothing to do
        }

        return nextChar;
    }

    private Queue<Character> stringToStack(String str) {
        final Queue<Character> result = new ArrayDeque<>(str.length());
        for(char c : str.toCharArray()){
            result.add(c);
        }
        return result;
    }

    private int appendNumeral(final StringBuilder result, int remaining, final int value, final String symbol) {
        while(0 < remaining && remaining >= value) {
            result.append(symbol);
            remaining -= value;
        }
        return remaining;
    }
}
