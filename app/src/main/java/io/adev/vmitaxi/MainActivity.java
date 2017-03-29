package io.adev.vmitaxi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import okhttp3.OkHttpClient;

import static io.reactivex.schedulers.Schedulers.io;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final OkHttpClient client = new OkHttpClient.Builder()
            .build();

    private Observable<String> createDbObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Thread.sleep(5000);
                e.onNext("db");
                e.onComplete();
            }
        });
    }

    private Observable<String> createCloudObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Thread.sleep(200);
                e.onNext("cloud");
                e.onComplete();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.btLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PreferLastObservable.create(
                        createDbObservable().subscribeOn(io()),
                        createCloudObservable().subscribeOn(io()))
                        .subscribeOn(io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new DisposableObserver<String>() {
                            @Override
                            public void onNext(String s) {
                                Log.e(TAG, s);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });

            }
        });

    }

    private static List<Integer> mock = Arrays.asList(1, 3, 4, 5, 6, 7);

    private static Observable<List<Integer>> createListObservable() {
        return Observable.just(mock);
    }

    private static Observable<List<String>> createStringObservable() {
        return createListObservable()
                .flatMap(new Function<List<Integer>, ObservableSource<List<String>>>() {
                    @Override
                    public ObservableSource<List<String>> apply(@NonNull List<Integer> integers) throws Exception {

                        List<String> result = new ArrayList<>();
                        for (Integer i : integers) {
                            result.add(i + "");
                        }

                        return Observable.just(result);
                    }
                });
    }

    private static Observable<Integer> createSenseObservable() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Thread.sleep(1000);
                e.onNext(42);
                e.onComplete();
            }
        });
    }

}
