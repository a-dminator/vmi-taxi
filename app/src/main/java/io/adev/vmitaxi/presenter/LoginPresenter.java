package io.adev.vmitaxi.presenter;

import io.adev.vmitaxi.data.LoginUseCase;
import io.adev.vmitaxi.presenter.contract.LoginContract;
import io.reactivex.observers.DisposableObserver;

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View view;
    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    private final LoginUseCase loginUseCase = new LoginUseCase();
    private DisposableObserver<Boolean> createLoginObserver() {
        return new DisposableObserver<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {
                view.displaySuccess();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    @Override
    public void onLogin(String login, String password) {
        loginUseCase.execute(createLoginObserver(), new LoginUseCase.Criteria(login, password));
    }

}
