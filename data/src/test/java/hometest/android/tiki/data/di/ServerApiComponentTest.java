package hometest.android.tiki.data.di;

import javax.inject.Singleton;

import dagger.Component;
import hometest.android.tiki.data.api.KeywordApiServiceTest;

@Singleton
@Component(modules = ServerApiModuleTest.class)
public interface ServerApiComponentTest {

    void inject(KeywordApiServiceTest apiServiceTest);
}
