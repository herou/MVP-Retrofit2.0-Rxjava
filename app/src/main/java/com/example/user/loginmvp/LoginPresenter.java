package com.example.user.loginmvp;

/**
 * Created by user on 10/16/2017.
 */

public interface LoginPresenter {
    void validateCred(String username,String password);
    void onDestroy();
    void onSuccess();
    void onFailure();
}
