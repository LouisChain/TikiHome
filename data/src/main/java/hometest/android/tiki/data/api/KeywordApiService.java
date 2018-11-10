package hometest.android.tiki.data.api;

import hometest.android.tiki.domain.model.Keyword;
import hometest.android.tiki.domain.service.ApiService;
import rx.Observable;

public class KeywordApiService implements ApiService {
    final HttpApiService mHttpService;

    public KeywordApiService(HttpApiService httpApiService) {
        this.mHttpService = httpApiService;
    }

    @Override
    public Observable<Keyword> getKeywords() {
        return mHttpService.getKeyword().flatMap(Observable::just);
    }
}
