package net.kaleidos.shortener

import grails.plugin.spock.*
import spock.lang.*

class ShortenerSpec extends Specification {

    @Unroll
    void 'generate short urls for some numbers: #num'() {
        setup:
            def shortener = new Shortener()

        when:
            def result = shortener.convert(num)

        then:
            result == shortUrl

        where:
            num   | shortUrl
              0   | '00000'
              4   | '00004'
              10  | '0000a'
              20  | '0000m'
              33  | '0000z'
              34  | '0000A'
              57  | '0000Z'
              58  | '00010'
              68  | '0001a'
    }

    void 'if the number is too long the lenght of the url will be > 5'() {
        setup:
            def shortener = new Shortener()

        when:
            def result = shortener.convert(num)

        then:
            result.length() > 5

        where:
            num = 1000000000
    }

}