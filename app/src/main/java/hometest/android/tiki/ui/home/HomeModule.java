package hometest.android.tiki.ui.home;

import dagger.Module;
import dagger.Provides;
import hometest.android.tiki.di.PerActivity;
import hometest.android.tiki.domain.usecase.KeywordUseCase;
import hometest.android.tiki.service.ToastUIService;

@Module
public class HomeModule {
    @Provides
    @PerActivity
    public ListKeywordViewModel provideListKeywordViewModel(KeywordUseCase keywordUseCase, ToastUIService toastUIService) {
        return new ListKeywordViewModel(keywordUseCase, toastUIService);
    }
}
