package io.adev.vmitaxi;

import android.util.Log;

import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.observers.DisposableObserver;

import static android.content.ContentValues.TAG;

public class PreferLastObservable {

    @SafeVarargs
    public static <T> Observable<T> create(final ObservableSource<T>... sources) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(final ObservableEmitter<T> emitter) throws Exception {

                final AtomicInteger currentSourceIndex = new AtomicInteger();

                for (int i = 0; i < sources.length; i++) {
                    ObservableSource<T> source = sources[i];
                    final int finalI = i;
                    source.subscribe(new DisposableObserver<T>() {
                        @Override
                        public void onNext(T t) {

                            Log.e(TAG, finalI + "");

                            if (currentSourceIndex.get() < finalI) {
                                currentSourceIndex.set(finalI);
                            }

                            if (finalI >= currentSourceIndex.get()) {
                                emitter.onNext(t);
                            }

                        }

                        @Override
                        public void onError(Throwable e) {
                            if (finalI == sources.length-1) {
                                emitter.onError(e);
                            }
                        }

                        @Override
                        public void onComplete() {
                            if (finalI == sources.length-1) {
                                emitter.onComplete();
                            }
                        }
                    });
                }

            }
        });
    }

}
