package net.kaleidos.shortener

import grails.plugin.spock.*
import spock.lang.*

class ShortenUrlSpec extends Specification {

    void 'save a short url for a target url'() {
        setup:
            def shortenUrl = new ShortenUrl(shortUrl: shortUrl, targetUrl: targetUrl)

        when:
            shortenUrl.save()

        then:
            shortenUrl.hasErrors() == false
            shortenUrl.id != null
            shortenUrl.shortUrl == shortUrl
            shortenUrl.targetUrl == targetUrl
            shortenUrl.hits == 0

        where:
            shortUrl = "abcde"
            targetUrl = "http://kaleidos.net"
    }
}