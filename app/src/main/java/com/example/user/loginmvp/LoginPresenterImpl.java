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
            loginModel.login(username,password,this);
        }
    }

    @Override
    public void onDestroy() {
        if(login != null){
            login = null;
        }
    }

    @Override
    public void onSuccess() {
        login.showLoggednSuccessfully();
    }

    @Override
    public void onFailure() {
        login.showTryAgain();
    }


    @Override
    public void checkUsernamePassword() {
        login.showTryAgain();
    }

    @Override
    public void onError() {
        login.showError();
    }

    @Override
    public void onSuccessfully() {
        login.showLoggednSuccessfully();
    }
}
