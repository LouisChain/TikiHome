package hometest.android.tiki.domain.di;

import dagger.Module;
import dagger.Provides;
import hometest.android.tiki.domain.service.ApiService;
import hometest.android.tiki.domain.service.LocalService;
import hometest.android.tiki.domain.usecase.KeywordUseCase;

@Module
public class DomainModule {
    @Provides
    public KeywordUseCase provideKeywordUseCase(LocalService localService, ApiService apiService) {
        return new KeywordUseCase(localService, apiService);
    }
}
