package hometest.android.tiki.domain.service;

import hometest.android.tiki.domain.model.Keyword;
import rx.Observable;

public interface LocalService {

    Observable<Keyword> getKeywords(int genTime);

    Observable<Void> saveKeywords(Keyword keyword);
}
