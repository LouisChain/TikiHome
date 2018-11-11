package hometest.android.tiki.ui.home;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.util.Log;

import java.net.UnknownHostException;

import hometest.android.tiki.domain.model.Keyword;
import hometest.android.tiki.domain.usecase.KeywordUseCase;
import hometest.android.tiki.service.ToastUIService;
import hometest.android.tiki.transformer.SchedulerTaskTransformer;
import rx.Subscription;

public class ListKeywordViewModel {
    private static final String TAG = ListKeywordViewModel.class.getSimpleName();

    public ObservableField<Keyword> keyword = new ObservableField<>();
    public ObservableBoolean refresh = new ObservableBoolean(false);
    public ObservableBoolean progress = new ObservableBoolean(false);

    private final KeywordUseCase mKeywordUseCase;
    private final ToastUIService mToastUIService;
    private Subscription mSubscription;

    public ListKeywordViewModel(KeywordUseCase keywordUseCase, ToastUIService toastUIService) {
        mKeywordUseCase = keywordUseCase;
        mToastUIService = toastUIService;
        loadKeyword();
    }

    public void loadKeyword() {
        progress.set(true);
        refresh.set(false);
        mSubscription = mKeywordUseCase.buildUseCase()
                .compose(new SchedulerTaskTransformer<>())
                .subscribe(
                        next -> {
                            if (next != null) {
                                refresh.set(false);
                                keyword.set(next);
                            } else {
                                refresh.set(true);
                            }
                            progress.set(false);
                        },
                        throwable -> {
                            if (throwable instanceof UnknownHostException) {
                                mToastUIService.showNetworkErrorToast();
                            }
                            refresh.set(true);
                            progress.set(false);
                        },
                        () -> Log.d(TAG, "Completed(keywors)")
                );
    }

    public void onDestroy() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
