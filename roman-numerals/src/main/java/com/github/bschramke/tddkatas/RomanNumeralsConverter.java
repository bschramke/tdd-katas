package com.github.bschramke.tddkatas;

public class RomanNumeralsConverter {
    public String fromArabic(int arabic) {
        if(arabic < 0) throw new IllegalArgumentException();
        if(arabic == 0) return "N";
        if(arabic == 1) return "I";
        if(arabic == 2) return "II";
        if(arabic == 3) return "III";

        return null;
    }
}
