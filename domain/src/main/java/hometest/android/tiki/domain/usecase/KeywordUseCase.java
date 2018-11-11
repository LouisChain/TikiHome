package hometest.android.tiki.domain.usecase;

import hometest.android.tiki.domain.model.Keyword;
import hometest.android.tiki.domain.service.ApiService;
import hometest.android.tiki.domain.service.LocalService;
import rx.Observable;

public class KeywordUseCase implements UseCase<Keyword> {
    private final LocalService mLocalService;
    private final ApiService mApiService;
    private int mId = 0;

    public KeywordUseCase(LocalService localService, ApiService apiService) {
        this.mLocalService = localService;
        this.mApiService = apiService;
    }

    public KeywordUseCase with(int id) {
        mId = id;
        return this;
    }

    @Override
    public Observable<Keyword> buildUseCase() {
        // priority get Keyword from api first if network is available, otherwise get from local
        return mApiService.getKeywords()
                .flatMap(keyword ->
                        mLocalService.saveKeywords(keyword)
                                .map(aVoid -> keyword))
                .onErrorResumeNext(throwable -> mLocalService.getKeywords(mId));
    }
}
