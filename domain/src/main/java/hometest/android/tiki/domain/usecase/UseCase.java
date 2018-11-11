package hometest.android.tiki.domain.usecase;

import rx.Observable;

public interface UseCase<T> {
    Observable<T> buildUseCase();
}
