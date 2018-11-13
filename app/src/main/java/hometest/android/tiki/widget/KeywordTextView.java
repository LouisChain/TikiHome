package hometest.android.tiki.widget;

import android.content.Context;
import android.util.AttributeSet;

import hometest.android.tiki.util.WordTokenizer;

public class KeywordTextView extends android.support.v7.widget.AppCompatTextView {

    private Context mContext;

    public KeywordTextView(Context context) {
        super(context);
        init(context);
    }

    public KeywordTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public KeywordTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
    }

    private CharSequence breakLines(CharSequence text) {
        StringBuilder formatText = new StringBuilder(text);
        String[] s = WordTokenizer.tokenize(text);
        if (s.length >= 2) {
            formatText = new StringBuilder();
            for (int i = 0; i < s.length / 2; i++) {
                formatText.append(s[i]);
                formatText.append(" ");
            }
            formatText.append("\n");
            for (int i = s.length / 2; i < s.length; i++) {
                formatText.append(s[i]);
                formatText.append(" ");
            }
            formatText.subSequence(0, formatText.length() - 1);
        }
        return formatText;
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        CharSequence formatText = breakLines(text);
        super.setText(formatText, type);
    }
}
