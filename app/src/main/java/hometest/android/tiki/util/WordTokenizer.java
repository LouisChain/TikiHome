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
}
