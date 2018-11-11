package hometest.android.tiki.data.di;

import javax.inject.Singleton;

import dagger.Component;
import hometest.android.tiki.domain.service.ApiService;

@Singleton
@Component(modules = DataModule.class)
public interface DataComponent {

    ApiService getApiService();
}
