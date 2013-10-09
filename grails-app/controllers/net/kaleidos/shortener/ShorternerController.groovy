package net.kaleidos.shortener

class ShortenerController {

    def urlShortenerService

    /**
     * Redirect to the target url from the short url
     *
     * @param shortUrl The shortUrl to redirect to it's target url
     */
    public redirectToTargetUrl(String shortUrl) {
        def targetUrl = urlShortenerService.getTargetUrl(shortUrl)
        if (targetUrl) {
            redirect url:targetUrl
        } else {
            response.status = 404
        }
    }
}