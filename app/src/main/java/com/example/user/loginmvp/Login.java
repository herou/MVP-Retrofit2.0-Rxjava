package com.example.user.loginmvp;

import android.view.View;

/**
 * Created by user on 10/16/2017.
 */

public interface Login {
    void showCheckFields();
    void showLoggednSuccessfully();
    void showTryAgain();
    void showError();
    void login(View view);
}
