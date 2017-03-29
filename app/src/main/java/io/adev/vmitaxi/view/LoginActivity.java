package io.adev.vmitaxi.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.adev.vmitaxi.R;
import io.adev.vmitaxi.presenter.LoginPresenter;
import io.adev.vmitaxi.presenter.contract.LoginContract;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private LoginContract.Presenter presenter = new LoginPresenter(this);

    private Button btLogin;
    private EditText edLogin;
    private EditText edPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edLogin = (EditText) findViewById(R.id.edLogin);
        edPassword = (EditText) findViewById(R.id.edPassword);

        btLogin = (Button) findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String login = edLogin.getText().toString();
                String password = edPassword.getText().toString();

                presenter.onLogin(login, password);
            }
        });

    }

    @Override
    public void displaySuccess() {
        btLogin.setText("Успех!");
    }

}
