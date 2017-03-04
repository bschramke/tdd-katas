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
    def "should return #roman when input is #arabic" () {
        given: "an instance of RomanNumeralsConverter"
            def converter = new RomanNumeralsConverter()

        expect: "on input #arabic the numeral is #roman"
            converter.fromArabic(arabic) == roman

        where:
            arabic || roman
            0      || 'N'
            1      || 'I'
    }
}
