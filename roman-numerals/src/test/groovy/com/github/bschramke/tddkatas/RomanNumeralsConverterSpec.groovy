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

        expect: "on input #value the symbol is #symbol"
            converter.fromArabic(value) == symbol

        where:
            value || symbol
            0      || 'N'
            1      || 'I'
            4      || 'IV'
            5      || 'V'
            9      || 'IX'
           10      || 'X'
           40      || 'XL'
           50      || 'L'
          100      || 'C'
          500      || 'D'
         1000      || 'M'
    }

    @Unroll
    def "should return #roman when input is #arabic" () {
        given: "an instance of RomanNumeralsConverter"
            def converter = new RomanNumeralsConverter()

        expect: "on input #arabic the numeral is #roman"
            converter.fromArabic(arabic) == roman

        where:
            arabic || roman
            3      || 'III'
            8      || 'VIII'
           13      || 'XIII'
           23      || 'XXIII'
    }
}
