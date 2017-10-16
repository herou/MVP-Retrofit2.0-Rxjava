package com.example.user.loginmvp;

/**
 * Created by user on 10/16/2017.
 */

public interface LoginModel {


    interface OnLoginFinishedListener{
        void checkUsernamePassword();
        void onError();
        void onSuccessfully();
    }
    void login(String username,String password,OnLoginFinishedListener onLoginFinishedListener);
}
