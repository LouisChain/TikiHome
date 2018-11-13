package hometest.android.tiki.util;

import java.util.StringTokenizer;

public class WordTokenizer {
    public static String[] tokenize(CharSequence paragraph) {
        if (paragraph == null || paragraph.toString().isEmpty()) {
            return new String[]{};
        }

        String delims = " \r\t\n,.;?";
        StringTokenizer st = new StringTokenizer(paragraph.toString(), delims);
        String[] words = new String[st.countTokens()];
        int i = 0;
        while (st.hasMoreElements()) {
            words[i] = st.nextElement().toString();
            i++;
        }
        return words;
    }

    public static CharSequence breakLines(CharSequence text) {
        StringBuilder formatText = new StringBuilder(text);
        String[] s = tokenize(text);
        if (s.length >= 2) {
            StringBuilder s1 = new StringBuilder();
            StringBuilder s2 = new StringBuilder();
            int length = s.length;
            int hLength = length / 2;
            for (int i = 0; i < hLength; i++) {
                s1.append(s[i]);
                if (i == hLength - 1) {
                    s1.append("\n");
                } else {
                    s1.append(" ");
                }
                s2.append(s[hLength + i]);
                s2.append(" ");
            }

            formatText = new StringBuilder(s1);
            formatText.append(s2);
        }
        return formatText;
    }
}
