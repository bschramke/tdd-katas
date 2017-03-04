package com.github.bschramke.tddkatas

import spock.lang.Specification
import spock.lang.Unroll

class RomanNumeralsConverterSpec extends Specification {

    def "should throw IllegalArgumentException when input is smaller than 0" () {
        given: "an instance of RomanNumeralsConverter"
            def converter = new RomanNumeralsConverter()

        when: "arabic number is smaller than 0"
            converter.fromArabic(-1)

        then: "a IllegalArgumentException should thrown"
            thrown IllegalArgumentException
    }

    @Unroll
    def "should return symbol #symbol for value #value" () {
        given: "an instance of RomanNumeralsConverter"
            def converter = new RomanNumeralsConverter()

        expect: "that on input #value the symbol is #symbol"
            converter.fromArabic(value) == symbol

        where:
            value || symbol
                0 || 'N'
                1 || 'I'
                4 || 'IV'
                5 || 'V'
                9 || 'IX'
               10 || 'X'
               40 || 'XL'
               50 || 'L'
               90 || 'XC'
              100 || 'C'
              400 || 'CD'
              500 || 'D'
              900 || 'CM'
             1000 || 'M'
    }

    @Unroll
    def "should return #roman when input is #arabic" () {
        given: "an instance of RomanNumeralsConverter"
            def converter = new RomanNumeralsConverter()

        expect: "that on input #arabic the numeral is #roman"
            converter.fromArabic(arabic) == roman

        where:
            arabic || roman
                 3 || 'III'
                 8 || 'VIII'
                13 || 'XIII'
                23 || 'XXIII'
                42 || 'XLII'
              1980 || 'MCMLXXX'
              1948 || 'MCMXLVIII'
    }

    @Unroll
    def "should return value #value for symbol #symbol" () {
        given: "an instance of RomanNumeralsConverter"
            def converter = new RomanNumeralsConverter()

        expect: "that on input #symbol the value is #value"
            converter.toArabic(symbol) == value

        where:
            value || symbol
                0 || 'N'
                1 || 'I'
                5 || 'V'
               10 || 'X'
               40 || 'XL'
               50 || 'L'
               90 || 'XC'
              100 || 'C'
              400 || 'CD'
              500 || 'D'
              900 || 'CM'
             1000 || 'M'
    }

    @Unroll
    def "should return #arabic when input is #roman" () {
        given: "an instance of RomanNumeralsConverter"
            def converter = new RomanNumeralsConverter()

        expect: "that on input #roman the result is #arabic"
            converter.toArabic(roman) == arabic

        where:
            arabic || roman
                 3 || 'III'
                 8 || 'VIII'
                23 || 'XXIII'
    }

    @Unroll
    def "should use subtraction when I is followed by V or X (#roman)" () {
        given: "an instance of RomanNumeralsConverter"
            def converter = new RomanNumeralsConverter()

        expect: "that on input #roman the result is #arabic"
            converter.toArabic(roman) == arabic

        where:
            arabic || roman
                 4 || 'IV'
                 9 || 'IX'
                14 || 'XIV'
                19 || 'XIX'
    }
}
