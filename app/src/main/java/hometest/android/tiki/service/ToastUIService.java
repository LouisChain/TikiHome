package hometest.android.tiki.service;

import android.content.Context;
import android.widget.Toast;

import hometest.android.tiki.R;

public class ToastUIService {
    private final Context mContext;

    public ToastUIService(Context context) {
        mContext = context.getApplicationContext();
    }

    public void showNetworkErrorToast() {
        showLongToast(mContext.getString(R.string.network_error));
    }

    private Toast showLongToast(CharSequence text) {
        Toast toast = Toast.makeText(mContext, text, Toast.LENGTH_LONG);
        toast.show();
        return toast;
    }

    private Toast showShortToast(CharSequence text) {
        Toast toast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
        toast.show();
        return toast;
    }
}
