package net.kaleidos.shortener

import grails.plugin.spock.*
import spock.lang.*

class ShortenUrlIntegrationSpec extends IntegrationSpec {

    void 'save a short url for a target url'() {
        setup:
            def shortenInstance = new ShortenUrl(shortUrl: shortUrl, targetUrl: targetUrl)

        when:
            shortenInstance.save()

        then:
            shortenInstance.hasErrors() == false
            shortenInstance.id != null
            shortenInstance.shortUrl == shortUrl
            shortenInstance.targetUrl == targetUrl
            shortenInstance.hits == 0

        where:
            shortUrl = "abcde"
            targetUrl = "http://kaleidos.net"
    }
}