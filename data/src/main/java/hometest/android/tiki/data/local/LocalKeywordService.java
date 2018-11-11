package hometest.android.tiki.data.local;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import hometest.android.tiki.data.local.model.LocalKeyword;
import hometest.android.tiki.data.local.model.LocalKeyword_Table;
import hometest.android.tiki.domain.converter.DomainConverter;
import hometest.android.tiki.domain.model.Keyword;
import hometest.android.tiki.domain.service.LocalService;
import rx.Observable;

public class LocalKeywordService implements LocalService {
    final DomainConverter<Keyword, LocalKeyword> mKeywordConverter;

    public LocalKeywordService(DomainConverter keywordConverter) {
        this.mKeywordConverter = keywordConverter;
    }

    @Override
    public Observable<Keyword> getKeywords(int id) {
        return Observable.fromCallable(() ->
                SQLite.select()
                        .from(LocalKeyword.class)
                        .where(LocalKeyword_Table.id.eq(id))
                        .querySingle());
    }

    @Override
    public Observable<Void> saveKeywords(Keyword keyword) {
        return Observable.fromCallable(() -> {
            LocalKeyword localMovie = mKeywordConverter.convert(keyword);
            localMovie.save();
            return null;
        });
    }
}
