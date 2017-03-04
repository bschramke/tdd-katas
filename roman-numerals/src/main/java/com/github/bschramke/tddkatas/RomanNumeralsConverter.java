package com.github.bschramke.tddkatas;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
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
        Stack<Character> charStack = stringToStack(roman);

        while(!charStack.empty()) {
            char ch = charStack.pop();
            result += Symbol.valueOf(String.valueOf(ch)).weight();
        }
        return result;
    }

    private Stack<Character> stringToStack(String str) {
        final Stack<Character> result = new Stack<>();
        for(char c : str.toCharArray()){
            result.push(c);
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
