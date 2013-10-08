package net.kaleidos.shortener

public class Shortener {

    def CHARS = ('0'..'9') +
                ('a'..'h') +
                ('j'..'k') +
                ('m'..'z') +
                ('A'..'H') +
                ('J'..'K') +
                ('M'..'Z')

    public String convert(Integer number) {
        return convertToBase(number, CHARS.size(), 0, "").padLeft(5, "0")
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