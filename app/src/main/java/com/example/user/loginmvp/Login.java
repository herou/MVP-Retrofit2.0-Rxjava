package com.example.user.loginmvp;

import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by user on 10/16/2017.
 */

public interface Login {
    void showCheckFields();
    void showLoggednSuccessfully();
    void showTryAgain();
    void showError();

    void showProgressDialog();
    void dissableProgressDialog();

}
