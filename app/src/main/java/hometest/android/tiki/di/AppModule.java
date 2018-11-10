package hometest.android.tiki.di;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hometest.android.tiki.data.di.DataModule;
import hometest.android.tiki.domain.di.DomainModule;

@Module(includes = {DataModule.class, DomainModule.class})
public class AppModule {
    final Application mApp;

    public AppModule(Application mApp) {
        this.mApp = mApp;
    }

    @Provides
    @Singleton
    public Context provideAppContext() {
        return mApp;
    }

    @Provides
    @Singleton
    public Resources provideResource(Application app) {
        return app.getResources();
    }
}
