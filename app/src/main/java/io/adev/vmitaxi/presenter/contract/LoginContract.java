package io.adev.vmitaxi.presenter.contract;

public interface LoginContract {
    interface Presenter {
        void onLogin(String login, String password);
    }
    interface View {
        void displaySuccess();
    }
}
