package com.example.user.loginmvp;

/**
 * Created by user on 10/16/2017.
 */

public class LoginPresenterImpl implements LoginPresenter,LoginModel.OnLoginFinishedListener{

    Login login;
    LoginModel loginModel;

    public LoginPresenterImpl(Login login) {
        this.login = login;
        loginModel = new LoginModelImpl();
    }

    @Override
    public void validateCred(String username, String password) {
        if(username.equalsIgnoreCase("") || password.equalsIgnoreCase("")){
            login.showCheckFields();
        }else{
            login.showProgressDialog();
            loginModel.login(username,password,this);
        }
    }


    @Override
    public void checkUsernamePassword() {
        login.dissableProgressDialog();
        login.showTryAgain();
    }

    @Override
    public void onError() {
        login.dissableProgressDialog();
        login.showError();
    }

    @Override
    public void onSuccessfully() {
        login.dissableProgressDialog();
        login.showLoggednSuccessfully();
    }
}
