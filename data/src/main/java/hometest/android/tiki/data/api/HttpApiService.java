package hometest.android.tiki.data.api;

import hometest.android.tiki.data.api.model.ApiKeyword;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import rx.Observable;

public interface HttpApiService {
    //@GET("/3/movie/{movie_id}")
    //@Headers("Cache-Control: no-cache")
    //Observable<ApiMovie> getKeyword(@Path("movie_id") int id, @Query("api_key") String key);
    @GET("/talenguyen/38b790795722e7d7b1b5db051c5786e5/raw/63380022f5f0c9a100f51a1e30887ca494c3326e/")
    @Headers("Cache-Control: no-cache")
    Observable<ApiKeyword> getKeyword();
}
