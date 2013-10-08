package net.kaleidos.shortener

class Shortener {

    def grailsApplication

    // These properties are set from Config.groovy
    def CHARS
    Integer MIN_LENGTH

    String toShort(Integer number) {
        return convertToBase(number, CHARS.size(), 0, "").padLeft(MIN_LENGTH, "0")
    }

    private String convertToBase(Integer number, Integer base, Integer position, String result) {
        if (number < Math.pow(base, position + 1)) {
            return CHARS[(number / (int)Math.pow(base, position)) as Integer] + result
        } else {
            int remainder = (number % (int)Math.pow(base, position + 1))
            return convertToBase (number - remainder, base, position + 1, CHARS[(remainder / (int)(Math.pow(base, position))) as Integer] + result)
        }
    }
}