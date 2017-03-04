package com.github.bschramke.tddkatas;

import java.util.ArrayDeque;
import java.util.EmptyStackException;
import java.util.Queue;
import java.util.Stack;

public class RomanNumeralsConverter {
    public enum Symbol {
        M(1000),CM(900),D(500),CD(400),C(100),XC(90),L(50),XL(40),X(10),IX(9),V(5),IV(4),I(1);

        private final int weight;

        Symbol(int weight) {
            this.weight = weight;
        }

        public int weight() {
            return weight;
        }

        public static boolean isSymbol(final String numeral) {
            try {
                Symbol.valueOf(numeral);
            }catch (IllegalArgumentException e) {
                return false;
            }

            return true;
        }
    }
    public String fromArabic(final int arabic) {
        if(arabic < 0) throw new IllegalArgumentException();
        if(arabic == 0) return "N";

        final StringBuilder result = new StringBuilder();
        int remaining = arabic;

        for(Symbol symbol : Symbol.values()) {
            remaining = appendNumeral(result, remaining, symbol.weight(), symbol.name());
        }

        return result.toString();
    }

    public int toArabic(final String roman) {
        final String input = roman.toUpperCase();

        if(input.equals("N")) return 0;
        if(Symbol.isSymbol(input)) return Symbol.valueOf(input).weight();

        int result = 0;
        Queue<Character> charStack = stringToStack(roman);

        while(!charStack.isEmpty()) {
            result += parseSymbol(charStack).weight();
        }

        return result;
    }

    private Symbol parseSymbol(final Queue<Character> charStack) {
        final StringBuilder builder = new StringBuilder(2);
        final char current = charStack.poll();
        final char next = stackPeek(charStack);

        builder.append(current);

        if(current == 'I') {
            if(next == 'V' || next == 'X'){
                builder.append(next);
                charStack.poll();
            }
        }

        return Symbol.valueOf(builder.toString());
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
