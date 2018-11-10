package hometest.android.tiki;

import android.app.Application;

import hometest.android.tiki.data.DataApp;
import hometest.android.tiki.di.AppComponent;
import hometest.android.tiki.di.AppModule;
import hometest.android.tiki.di.DaggerAppComponent;

public class App extends Application {
    AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        DataApp.init(this);
    }

    public AppComponent getAppComponent() {
        if (mAppComponent == null) {
            mAppComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();
        }
        return mAppComponent;
    }
}
