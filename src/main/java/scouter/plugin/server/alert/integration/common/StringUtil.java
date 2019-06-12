package scouter.plugin.server.alert.integration.common;

public class StringUtil {

    private StringUtil() {
        throw new IllegalStateException("utility class");
    }

    public static String getValue(String message, String attr) {
        try {
            int length = attr.length() + 1;
            int beginIndex = message.indexOf(attr);
            return message.substring(beginIndex + length, message.indexOf(' ', beginIndex));
        } catch (Exception e) {
            return "";
        }
    }

    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }

}
