package hometest.android.tiki.ui.home;

import hometest.android.tiki.domain.usecase.KeywordUseCase;

public class HomeViewModel {
    private final KeywordUseCase mKeywordUseCase;

    public HomeViewModel(KeywordUseCase keywordUseCase) {
        this.mKeywordUseCase = keywordUseCase;
    }
}
