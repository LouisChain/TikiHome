package hometest.android.tiki.data.local;

import android.os.Build;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.annotation.internal.DoNotInstrument;

import java.util.ArrayList;

import hometest.android.tiki.data.BuildConfig;
import hometest.android.tiki.data.DataApp;
import hometest.android.tiki.data.di.DataComponent;
import hometest.android.tiki.data.local.converter.LocalKeywordConverter;
import hometest.android.tiki.data.local.model.LocalKeyword;
import hometest.android.tiki.domain.model.Keyword;
import rx.observers.TestSubscriber;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP, manifest = Config.NONE)
@DoNotInstrument
public class KeywordDatabseServiceTest {
    DataComponent mDataComponent;

    LocalKeywordService mLocalService;

    @Before
    public void setup() {
        if (mDataComponent == null) {
            LocalKeywordConverter converter = new LocalKeywordConverter();
            mLocalService = new LocalKeywordService(converter);
            DataApp.init(RuntimeEnvironment.application);
        }
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testSaveAndGetKeyword() {
        ArrayList<String> toTest = new ArrayList<>();
        toTest.add("KeyA");
        toTest.add("KeyB");
        toTest.add("KeyC");
        LocalKeyword expectedKeyword = new LocalKeyword(0, toTest);

        TestSubscriber<Void> saveSubscriber = new TestSubscriber<>();
        mLocalService.saveKeywords(expectedKeyword)
                .subscribe(saveSubscriber);
        saveSubscriber.awaitTerminalEvent();
        saveSubscriber.assertNoErrors();
        saveSubscriber.assertCompleted();
        saveSubscriber.assertValue(null);

        TestSubscriber<Keyword> getSubscriber = new TestSubscriber<>();
        mLocalService.getKeywords(expectedKeyword.getId())
                .subscribe(getSubscriber);
        getSubscriber.awaitTerminalEvent();
        getSubscriber.assertCompleted();
        getSubscriber.assertNoErrors();
        getSubscriber.assertValueCount(1);
        Keyword actualMovie = getSubscriber.getOnNextEvents().get(0);
        Assert.assertEquals(expectedKeyword.getId(), actualMovie.getId());
        Assert.assertEquals(expectedKeyword.getData(), actualMovie.getData());
    }
}
