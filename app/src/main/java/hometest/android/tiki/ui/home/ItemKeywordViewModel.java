package hometest.android.tiki.ui.home;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.graphics.Color;

import java.util.Random;

public class ItemKeywordViewModel extends BaseObservable {
    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableInt color = new ObservableInt();

    private String mKeyword;
    private Context mContext;

    public ItemKeywordViewModel(Context context, String keyword) {
        mContext = context;
        mKeyword = keyword;
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        this.color.set(color);
    }

    public void bind() {
        title.set(mKeyword);
    }
}
