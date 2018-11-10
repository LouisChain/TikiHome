package hometest.android.tiki.data.di;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hometest.android.tiki.data.api.HttpApiService;
import hometest.android.tiki.data.api.KeywordApiService;
import hometest.android.tiki.data.local.LocalKeywordService;
import hometest.android.tiki.data.local.converter.LocalKeywordConverter;
import hometest.android.tiki.data.local.model.LocalKeyword;
import hometest.android.tiki.domain.converter.DomainConverter;
import hometest.android.tiki.domain.model.Keyword;
import hometest.android.tiki.domain.service.ApiService;
import hometest.android.tiki.domain.service.LocalService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DataModule {
    public DataModule() {

    }

    @Provides
    @Singleton
    public DomainConverter<Keyword, LocalKeyword> provideDomainConverter() {
        return new LocalKeywordConverter();
    }

    @Provides
    @Singleton
    public LocalService provideKeywordService(DomainConverter<Keyword, LocalKeyword> keywordConverter) {
        return new LocalKeywordService(keywordConverter);
    }

    @Provides
    @Singleton
    public ApiService provideApiService(OkHttpClient okHttpClient, Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gist.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        return new KeywordApiService(retrofit.create(HttpApiService.class));
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttp() {
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(35, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }
}
