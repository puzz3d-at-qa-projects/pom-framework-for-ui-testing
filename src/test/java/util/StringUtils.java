package util;

import java.util.regex.Pattern;

public class StringUtils {

    public static final String PATTERN_ERROR = "Pattern not found";

    public static String stripPrice(String text) {
        var p = Pattern.compile("(?<=USD ).*.\\d{2}");
        var m = p.matcher(text);
        return m.find() ? m.group(0) : PATTERN_ERROR;
    }
}
