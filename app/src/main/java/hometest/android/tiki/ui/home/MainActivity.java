package hometest.android.tiki.ui.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import hometest.android.tiki.R;
import hometest.android.tiki.databinding.ActivityMainBinding;
import hometest.android.tiki.ui.BaseActivity;

public class MainActivity extends BaseActivity {
    private ListKeywordViewModel mListKeywordViewModel;
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mListKeywordViewModel = getAppComponent().plus(new HomeModule()).getListKeywordViewModel();
        mBinding.setViewModel(mListKeywordViewModel);
        setupRecyclerView(mBinding);
    }

    private void setupRecyclerView(ActivityMainBinding binding) {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        binding.rvKeyword.setHasFixedSize(true);
        binding.rvKeyword.setLayoutManager(llm);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mListKeywordViewModel.onDestroy();
    }
}
