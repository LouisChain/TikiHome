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

    @Override
    public void setText(CharSequence text, BufferType type) {
        CharSequence formatText = WordTokenizer.breakLines(text);
        super.setText(formatText, type);
    }
}
