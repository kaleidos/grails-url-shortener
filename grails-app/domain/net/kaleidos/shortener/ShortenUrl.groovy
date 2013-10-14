package net.kaleidos.shortener

class ShortenUrl {

    /**
     * The short link (without domain)
     */
    String shortUrl

    /**
     * The final url
     */
    String targetUrl

    /**
     * The times the short url has been converted
     */
    Integer hits = 0

    /**
     * Creation date
     */
    Date dateCreated

    /**
     * Last updated
     */
    Date lastUpdated

    static constraints = {
        targetUrl url:['localhost:?\\d{0,4}'], type: "text", unique: true
    }

    static mapping = {
        version false

        // Indexes
        email shortUrl: 'shortener_url__short_url_idx'
        email targetUrl: 'shortener_url__target_url_idx'
    }
}