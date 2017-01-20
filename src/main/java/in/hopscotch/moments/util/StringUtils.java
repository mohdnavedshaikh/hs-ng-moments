package in.hopscotch.moments.util;

public final class StringUtils {
    private StringUtils() {
    }

    public static int compare(String text1, String text2) {
        if (text1 == null && text2 == null)
            return 0;
        if (text1 != null && text2 == null) {
            return 1;
        }
        if (text1 == null) {
            return -1;
        }
        return text1.compareTo(text2);
    }

    public static boolean hasText(String text) {
        if (text == null)
            return false;
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isWhitespace(text.charAt(i)))
                return true;
        }
        return false;
    }

    public static boolean equals(String text1, String text2) {
        if (text1 == null)
            return text2 == null;

        return text1.equals(text2);
    }

    public static String truncate(String text, int maxLength) {
        if (text == null)
            return null;
        if (text.length() <= maxLength)
            return text;
        return text.substring(0, maxLength);
    }

    public static String trim(String text) {
        if (text == null)
            return null;
        return text.trim();
    }

    public static String lPadZero(int in, int fill) {
        boolean negative = false;
        int value;
        int len = 0;
        int newIn = in;

        if (in >= 0) {
            value = in;
        } else {
            negative = true;
            value = -in;
            newIn = -in;
            len++;
        }

        if (value == 0) {
            len = 1;
        } else {
            for (; value != 0; len++) {
                value /= 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        if (negative) {
            sb.append('-');
        }

        for (int i = fill; i > len; i--) {
            sb.append('0');
        }

        sb.append(newIn);
        return sb.toString();
    }
}

