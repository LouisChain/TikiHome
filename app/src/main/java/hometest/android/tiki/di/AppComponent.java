package hometest.android.tiki.di;

import javax.inject.Singleton;

import dagger.Component;
import hometest.android.tiki.ui.home.HomeComponent;
import hometest.android.tiki.ui.home.HomeModule;


@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {
    HomeComponent plus(HomeModule listMovieModule);
}
