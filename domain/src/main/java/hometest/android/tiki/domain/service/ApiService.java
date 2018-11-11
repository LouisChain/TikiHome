package hometest.android.tiki.domain.service;

import hometest.android.tiki.domain.model.Keyword;
import rx.Observable;

public interface ApiService {
    Observable<Keyword> getKeywords();
}
