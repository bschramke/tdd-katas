package com.github.bschramke.tddkatas;

public class RomanNumeralsConverter {
    public enum Symbol {
        L(50),XL(40),X(10),IX(9),V(5),IV(4),I(1);

        private final int weight;

        Symbol(int weight) {
            this.weight = weight;
        }

        public int weight() {
            return weight;
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

    private int appendNumeral(final StringBuilder result, int remaining, final int value, final String symbol) {
        while(0 < remaining && remaining >= value) {
            result.append(symbol);
            remaining -= value;
        }
        return remaining;
    }
}
