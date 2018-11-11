package hometest.android.tiki.data.api.transformer;


import hometest.android.tiki.data.api.exception.ApiException;
import hometest.android.tiki.data.api.response.KeywordApiResponse;
import rx.Observable;

public class KeywordApiTransformer<T> implements Observable.Transformer<KeywordApiResponse<T>, T> {
    @Override
    public Observable<T> call(Observable<KeywordApiResponse<T>> keywordApiTransformerObservable) {
        return keywordApiTransformerObservable.flatMap(tResponse -> {
            if (tResponse.getResult() != null) {
                return Observable.just(tResponse.getResult());
            } else {
                return Observable.error(new ApiException());
            }
        });
    }
}
