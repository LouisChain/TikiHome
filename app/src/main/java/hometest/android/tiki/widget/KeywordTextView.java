package hometest.android.tiki.widget;

import android.content.Context;
import android.util.AttributeSet;

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
        setMaxLines(2);
        String formatText = text.toString();
        String s = text.toString().trim();
        String[] ss = s.split(" ");
        if (ss != null && ss.length >= 2) {
            formatText = "";
            for (int i = 0; i < ss.length / 2; i++) {
                formatText += ss[i] + " ";
            }
            formatText += "\n";
            for (int i = ss.length / 2; i < ss.length; i++) {
                formatText += ss[i] + " ";
            }
        }

        super.setText(formatText, type);
    }
}
