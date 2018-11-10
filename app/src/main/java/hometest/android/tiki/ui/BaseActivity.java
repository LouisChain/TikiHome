package hometest.android.tiki.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import hometest.android.tiki.App;
import hometest.android.tiki.di.AppComponent;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected AppComponent getAppComponent() {
        return ((App) getApplicationContext()).getAppComponent();
    }
}
