package hometest.android.tiki.data;

import android.content.Context;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

public class DataApp {
    public DataApp() {

    }

    public static void init(Context context) {
        FlowManager.init(new FlowConfig.Builder(context).build());
    }
}
