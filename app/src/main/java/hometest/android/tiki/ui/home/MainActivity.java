package hometest.android.tiki.ui.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import hometest.android.tiki.R;
import hometest.android.tiki.databinding.ActivityMainBinding;
import hometest.android.tiki.ui.BaseActivity;

public class MainActivity extends BaseActivity {
    private HomeViewModel mHomeViewModel;
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mHomeViewModel = getAppComponent().plus(new HomeModule()).getHomeViewModel();
        mBinding.setViewModel(mHomeViewModel);
    }
}
