package com.github.bschramke.tddkatas;

public class RomanNumeralsConverter {
    public String fromArabic(final int arabic) {
        if(arabic < 0) throw new IllegalArgumentException();
        if(arabic == 0) return "N";

        final StringBuilder result = new StringBuilder();
        int remaining = arabic;

        if(remaining >= 4) {
            result.append("IV");
            remaining -= 4;
        }
        while(0 < remaining--){
            result.append('I');
        }

        return result.toString();
    }
}
