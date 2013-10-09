package net.kaleidos.shortener

import javax.annotation.PostConstruct

class UrlShortenerService {
    static transactional = true

    def grailsApplication
    def sequenceGenerator

    List<String> CHARS
    Integer MIN_LENGTH

    @PostConstruct
    public init() {
        CHARS = grailsApplication.config.shortener.characters
        MIN_LENGTH = grailsApplication.config.shortener.minLength
    }

    /**
     * Genereate a short url for a given url
     *
     * @param targetUrl The url to shorten
     * @return the shorted url
     */
    public String shortUrl(String targetUrl) {

        def shortenInstance = ShortenUrl.findByTargetUrl(targetUrl)
        if (shortenInstance) {
            return shortenInstance.shortUrl
        }

        Long nextNumber = sequenceGenerator.getNextNumber()

        def shortUrl = this.convert(nextNumber)
        shortenInstance = new ShortenUrl(targetUrl: targetUrl, shortUrl:shortUrl)
        shortenInstance.save()

        if (!shortenInstance.hasErrors()) {
            return shortUrl
        } else {
            return null
        }
    }

    /**
     * Get the target url from a short url and increment the number of hits
     *
     * @param shortUrl The short url to "expand"
     * @return the target url or null if it doesn't exist
     */
    public String getTargetUrl(String shortUrl) {
        def shortenInstance = ShortenUrl.findByShortUrl(shortUrl)
        if (shortenInstance) {
            shortenInstance.hits++
            shortenInstance.save()
        }

        return shortenInstance?.targetUrl
    }

    private String convert(Long number) {
        return convertToBase(number, CHARS.size(), 0, "").padLeft(MIN_LENGTH, CHARS[0])
    }

    private String convertToBase(Long number, Integer base, Integer position, String result) {
        if (number < Math.pow(base, position + 1)) {
            return CHARS[(number / (long)Math.pow(base, position)) as Integer] + result
        } else {
            Long remainder = (number % (long)Math.pow(base, position + 1))
            return convertToBase (number - remainder, base, position + 1, CHARS[(remainder / (long)(Math.pow(base, position))) as Integer] + result)
        }
    }
}