package com.github.bschramke.tddkatas

import spock.lang.Specification

class RomanNumeralsConverterSpec extends Specification {

    def "should throw IllegalArgumentException when input is smaller than 0" () {
        given: "an instance of RomanNumeralsConverter"
            def converter = new RomanNumeralsConverter()

        when: "arabic number is smaller than 0"
            converter.fromArabic(-1)

        then: "a IllegalArgumentException should thrown"
            thrown IllegalArgumentException
    }
}
