package com.example.user.loginmvp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class LoginActivityImpl extends AppCompatActivity implements Login{

    EditText username;
    EditText password;
    Button login;
    LoginPresenterImpl loginPresenterImpl;
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginPresenterImpl = new LoginPresenterImpl(this);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        loading = (ProgressBar) findViewById(R.id.loading);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenterImpl.validateCred(username.getText().toString(),password.getText().toString());
            }
        });


    }

    @Override
    public void showCheckFields() {
        Toast.makeText(getApplicationContext(),"Check Username or Password!",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoggednSuccessfully() {
        Toast.makeText(getApplicationContext(),"You are logged successfully",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showTryAgain() {
        Toast.makeText(getApplicationContext(),"Try again,username and password are incorrect!",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError() {
        Toast.makeText(getApplicationContext(),"Try again!",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressDialog() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void dissableProgressDialog() {
        loading.setVisibility(View.INVISIBLE);
    }

}
