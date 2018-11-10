package hometest.android.tiki.domain.usecase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import hometest.android.tiki.domain.model.Keyword;
import hometest.android.tiki.domain.service.ApiService;
import hometest.android.tiki.domain.service.LocalService;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class KeywordTest {
    private KeywordUseCase mKeywordUseCase;
    @Mock
    private LocalService mLocalService;
    @Mock
    private ApiService mApiService;

    @Before
    public void setup() {
        mKeywordUseCase = spy(new KeywordUseCase(mLocalService, mApiService));
    }

    @After
    public void tearDown() {
        mKeywordUseCase = null;
    }

    private Keyword mockNewKeyword(final int id, final List<String> data) {
        Keyword keyword = mock(Keyword.class);
        when(keyword.getId()).thenReturn(id);
        when(keyword.getData()).thenReturn(data);

        return keyword;
    }

    @Test
    public void testApiSuccess() {
        Keyword expectedKeyword = mockNewKeyword(0, Arrays.asList("A", "B", "C"));
        when(mApiService.getKeywords()).thenReturn(Observable.just(expectedKeyword));
        when(mLocalService.saveKeywords(any())).thenReturn(Observable.just(null));

        TestSubscriber<Keyword> testSubscriber = new TestSubscriber<>();
        mKeywordUseCase
                .buildUseCase()
                .subscribe(testSubscriber);

        verify(mApiService).getKeywords();
        verify(mLocalService).saveKeywords(any());
        verify(mLocalService, never()).getKeywords(anyInt());
        testSubscriber.assertValue(expectedKeyword);
        testSubscriber.assertValueCount(1);
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        Keyword actualKeyword = testSubscriber.getOnNextEvents().get(0);
        Assert.assertEquals(expectedKeyword.getData().size(), actualKeyword.getData().size());
        for (int i = 0; i < actualKeyword.getData().size(); i++) {
            Assert.assertEquals(expectedKeyword.getData().get(i), actualKeyword.getData().get(i));
        }
    }

    @Test
    public void testApiFailure_LocalSuccess() {
        Keyword expectedKeyword = mockNewKeyword(0, Arrays.asList("A", "B", "C"));
        when(mApiService.getKeywords()).thenReturn(Observable.error(new Exception()));
        when(mLocalService.getKeywords(anyInt())).thenReturn(Observable.just(expectedKeyword));

        TestSubscriber<Keyword> testSubscriber = new TestSubscriber<>();
        mKeywordUseCase
                .buildUseCase()
                .subscribe(testSubscriber);

        verify(mApiService).getKeywords();
        verify(mLocalService, never()).saveKeywords(any());
        verify(mLocalService).getKeywords(anyInt());
        testSubscriber.assertCompleted();
        testSubscriber.assertUnsubscribed();
        testSubscriber.assertNoErrors();
        testSubscriber.assertValue(expectedKeyword);
        testSubscriber.assertValueCount(1);
        Keyword actualKeyword = testSubscriber.getOnNextEvents().get(0);
        Assert.assertEquals(expectedKeyword.getData().size(), actualKeyword.getData().size());
        for (int i = 0; i < actualKeyword.getData().size(); i++) {
            Assert.assertEquals(expectedKeyword.getData().get(i), actualKeyword.getData().get(i));
        }
    }

    @Test
    public void testApiFailure_LocalFailure() {
        Exception apiException = new Exception("Api exception");
        Exception localException = new Exception("Local Exception");
        when(mApiService.getKeywords()).thenReturn(Observable.error(apiException));
        when(mLocalService.getKeywords(anyInt())).thenReturn(Observable.error(localException));

        TestSubscriber<Keyword> testSubscriber = new TestSubscriber<>();
        mKeywordUseCase
                .buildUseCase()
                .subscribe(testSubscriber);

        verify(mApiService).getKeywords();
        verify(mLocalService, never()).saveKeywords(any());
        verify(mLocalService).getKeywords(anyInt());
        testSubscriber.assertError(localException);
        testSubscriber.assertNotCompleted();
    }
}
