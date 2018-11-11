package hometest.android.tiki.ui.home;


import dagger.Subcomponent;
import hometest.android.tiki.di.PerActivity;

@Subcomponent(modules = HomeModule.class)
@PerActivity
public interface HomeComponent {
//    HomeViewModel getHomeViewModel();
    ListKeywordViewModel getListKeywordViewModel();
}
