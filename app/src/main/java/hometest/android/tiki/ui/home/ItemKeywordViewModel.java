package hometest.android.tiki.ui.home;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import java.util.Random;

import hometest.android.tiki.R;

public class ItemKeywordViewModel extends BaseObservable {
    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableInt color = new ObservableInt();

    private String mKeyword;
    private Context mContext;

    public ItemKeywordViewModel(Context context, String keyword) {
        mContext = context;
        mKeyword = keyword;
        setRandomColorBackground();
    }

    private void setRandomColorBackground() {
        int[] androidColors = mContext.getResources().getIntArray(R.array.randomColors);
        int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
        this.color.set(randomAndroidColor);
    }

    public void bind() {
        title.set(mKeyword);
    }
}
