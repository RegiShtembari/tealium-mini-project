package utils;

public class PriceUtils {

    public static double parsePrice(String text) {
        return Double.parseDouble(text.replace("$", "").trim());
    }
}
