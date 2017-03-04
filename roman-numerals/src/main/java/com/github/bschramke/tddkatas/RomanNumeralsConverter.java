package com.github.bschramke.tddkatas;

public class RomanNumeralsConverter {
    public String fromArabic(final int arabic) {
        if(arabic < 0) throw new IllegalArgumentException();
        if(arabic == 0) return "N";

        final StringBuilder result = new StringBuilder();
        int remaining = arabic;

        remaining = appendNumeral(result, remaining, 40, "XL");
        remaining = appendNumeral(result, remaining, 10, "X");
        remaining = appendNumeral(result, remaining, 9, "IX");
        remaining = appendNumeral(result, remaining, 5, "V");
        remaining = appendNumeral(result, remaining, 4, "IV");
        while(0 < remaining--){
            result.append('I');
        }

        return result.toString();
    }

    private int appendNumeral(final StringBuilder result, int remaining, final int value, final String symbol) {
        while(remaining >= value) {
            result.append(symbol);
            remaining -= value;
        }
        return remaining;
    }
}
