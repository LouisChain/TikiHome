package hometest.android.tiki.data.api;

import android.os.Build;

import com.google.gson.Gson;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.annotation.internal.DoNotInstrument;

import javax.inject.Inject;

import hometest.android.tiki.data.BuildConfig;
import hometest.android.tiki.data.api.model.ApiKeyword;
import hometest.android.tiki.data.di.DaggerServerApiComponentTest;
import hometest.android.tiki.data.di.ServerApiComponentTest;
import hometest.android.tiki.data.di.ServerApiModuleTest;
import hometest.android.tiki.domain.model.Keyword;
import hometest.android.tiki.domain.service.ApiService;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import rx.observers.TestSubscriber;

import static junit.framework.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class,
        sdk = Build.VERSION_CODES.LOLLIPOP,
        manifest = Config.NONE,
        shadows = TestPolicy.MyNetworkSecurityPolicy.class)
@DoNotInstrument
public class KeywordApiServiceTest {
    private ServerApiComponentTest mApiComponentTest;

    @Inject
    ApiService mApiService;

    @Inject
    Gson mGson;

    @Inject
    MockWebServer mMockWebServer;

    @Before
    public void setup() {
        mApiComponentTest = DaggerServerApiComponentTest.builder()
                .serverApiModuleTest(new ServerApiModuleTest())
                .build();
        mApiComponentTest.inject(this);
    }

    @After
    public void tearDown() throws Exception {
        mMockWebServer.shutdown();
    }

    public String getSingleMovieBody() {
        return "[\n" +
                "        \"xiaomi\",\n" +
                "                \"bitis hunter\",\n" +
                "                \"bts\",\n" +
                "                \"balo\",\n" +
                "                \"bitis hunter x\",\n" +
                "                \"tai nghe\",\n" +
                "                \"harry potter\",\n" +
                "                \"anker\",\n" +
                "                \"iphone\",\n" +
                "                \"balo nữ\",\n" +
                "                \"nguyễn nhật ánh\",\n" +
                "                \"đắc nhân tâm\",\n" +
                "                \"ipad\",\n" +
                "                \"senka\",\n" +
                "                \"tai nghe bluetooth\",\n" +
                "                \"son\",\n" +
                "                \"maybelline\",\n" +
                "                \"laneige\",\n" +
                "                \"kem chống nắng\",\n" +
                "                \"anh chính là thanh xuân của em\"\n" +
                "]";
    }

    @Test
    public void testGetKeyword() throws Exception {
        ApiKeyword expectedMovie = mGson.fromJson(getSingleMovieBody(), ApiKeyword.class);
        mMockWebServer.enqueue(new MockResponse().setBody(getSingleMovieBody()));
        TestSubscriber<Keyword> testSubscriber = new TestSubscriber<>();
        //TestObserver<Movie> testObserver = new TestObserver<>();
        mApiService.getKeywords()
                .subscribe(testSubscriber);
        testSubscriber.awaitTerminalEvent();
        testSubscriber.assertCompleted();
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);
        testSubscriber.assertUnsubscribed();
        Keyword actualMovie = testSubscriber.getOnNextEvents().get(0);
        Assert.assertEquals(expectedMovie.getId(), actualMovie.getId());

        // test request path, headers, query and others
        RecordedRequest request = null;
        try {
            request = mMockWebServer.takeRequest();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals("/talenguyen/38b790795722e7d7b1b5db051c5786e5/raw/63380022f5f0c9a100f51a1e30887ca494c3326e/", request.getPath());
        assertEquals("GET", request.getMethod());
        assertEquals("no-cache", request.getHeader("Cache-Control"));

    }
}
