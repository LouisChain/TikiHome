package hometest.android.tiki.ui.home;

import dagger.Module;
import dagger.Provides;
import hometest.android.tiki.di.PerActivity;
import hometest.android.tiki.domain.usecase.KeywordUseCase;

@Module
public class HomeModule {
    @Provides
    @PerActivity
    public HomeViewModel provideHomeViewModel(KeywordUseCase keywordUseCase) {
        return new HomeViewModel(keywordUseCase);
    }
}
