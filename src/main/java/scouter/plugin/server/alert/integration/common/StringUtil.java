package scouter.plugin.server.alert.integration.common;

public class StringUtil {

    private StringUtil() {
    }

    public static String getValue(String message, String attr) {
        try {
            String str = attr + "=";
            int length = str.length();
            int beginIndex = message.indexOf(str);
            if (beginIndex == -1) {
                return "";
            }
            return message.substring(beginIndex + length, message.indexOf(' ', beginIndex));
        } catch (Exception e) {
            return "";
        }
    }

    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }

}
