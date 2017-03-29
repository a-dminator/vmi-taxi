package io.adev.vmitaxi.data;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.adev.vmitaxi.UseCase;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.Request;

public class LoginUseCase extends UseCase<Boolean, LoginUseCase.Criteria> {

    public static final class Criteria {
        public final String login;
        public final String password;
        public Criteria(String login, String password) {
            this.login = login;
            this.password = password;
        }
    }

    public LoginUseCase() {
        super(Schedulers.io());
    }

    @Override
    protected Observable<Boolean> buildObservable(final Criteria criteria) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {

                HttpUrl url = HttpUrl.parse("http://89.223.29.6:8080/taxi/users/login").newBuilder()
                        .addEncodedQueryParameter("login", criteria.login)
                        .addEncodedQueryParameter("password", criteria.password)
                        .build();

                Request request = new Request.Builder()
                        .url(url)
                        .get()
                        .build();

                String response = OkHttp.CLIENT.newCall(request)
                        .execute()
                        .body().string();

                JsonObject responseJson = new JsonParser().parse(response).getAsJsonObject().get("response").getAsJsonObject();

                e.onNext(true);
                e.onComplete();
            }
        });
    }

}
