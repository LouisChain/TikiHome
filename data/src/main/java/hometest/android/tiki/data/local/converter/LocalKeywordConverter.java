package hometest.android.tiki.data.local.converter;

import hometest.android.tiki.data.local.model.LocalKeyword;
import hometest.android.tiki.domain.converter.DomainConverter;
import hometest.android.tiki.domain.model.Keyword;

public class LocalKeywordConverter implements DomainConverter<Keyword, LocalKeyword> {
    @Override
    public LocalKeyword convert(Keyword from) {
        return from instanceof LocalKeyword
                ? (LocalKeyword) from
                : new LocalKeyword(from.getId(), from.getData());
    }
}
