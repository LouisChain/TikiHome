package hometest.android.tiki.ui.home;

import android.databinding.ObservableField;
import android.util.Log;

import hometest.android.tiki.domain.model.Keyword;
import hometest.android.tiki.domain.usecase.KeywordUseCase;
import hometest.android.tiki.transformer.SchedulerTaskTransformer;
import rx.Subscription;

public class ListKeywordViewModel {
    private static final String TAG = ListKeywordViewModel.class.getSimpleName();
    public ObservableField<Keyword> keyword = new ObservableField<>();
    private final KeywordUseCase mKeywordUseCase;
    private Subscription mSubscription;

    public ListKeywordViewModel(KeywordUseCase keywordUseCase) {
        mKeywordUseCase = keywordUseCase;
        loadKeyword();
    }

    private void loadKeyword() {
        mSubscription = mKeywordUseCase.buildUseCase()
                .compose(new SchedulerTaskTransformer<>())
                .subscribe(
                        next -> {
                            if (next != null) {
                                keyword.set(next);
                            }
                        },
                        error -> Log.d(TAG, "Error(keyword):" + error),
                        () -> Log.d(TAG, "Completed(keywors)")
                );
    }

    public void onDestroy() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
