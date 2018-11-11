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
import hometest.android.tiki.data.api.model.ApiKeyword;
import hometest.android.tiki.data.api.model.gson.KeywordModelDeserializer;
import hometest.android.tiki.domain.service.ApiService;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ServerApiModuleTest {
    @Singleton
    @Provides
    public MockWebServer provideMockWebServer() {
        return new MockWebServer();
    }

    @Provides
    @Singleton
    public ApiService provideApiService(MockWebServer mockWebServer, OkHttpClient okHttpClient, Gson gson) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(mockWebServer.url("/").toString())
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
        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(ApiKeyword.class, new KeywordModelDeserializer());
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }
}
