package com.example.loginmvp.ui.login;


import android.util.Log;

import com.example.loginmvp.data.network.ApiHelper;
import com.example.loginmvp.data.network.AppApiHelper;

import com.google.gson.JsonObject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by user on 10/16/2017.
 */

public class LoginPresenterImpl implements LoginPresenter {

    Login loginView;
    ApiHelper apiHelper;

    public LoginPresenterImpl(Login login) {
        this.loginView = login;
        apiHelper = new AppApiHelper();
    }
    @Override
    public void validateCred(String username, String password) {
        if(username.equalsIgnoreCase("") || password.equalsIgnoreCase("")){
            loginView.showCheckFields();
        }else {
            loginView.showProgressDialog();

            apiHelper.login(username, password)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<JsonObject>() {
                        @Override
                        public void onCompleted() {
                            //
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("Problem : ", e.getMessage());
                        }

                        @Override
                        public void onNext(JsonObject jsonObject) {
                            String response = jsonObject.get("status").toString();
                            Log.d("",""+response);
                            if(response.equalsIgnoreCase("\"error\"")){
                                loginView.dissableProgressDialog();
                                loginView.showTryAgain();


                            }else if(response.equalsIgnoreCase( "\"success\"")){
                                loginView.dissableProgressDialog();
                                loginView.showLoggednSuccessfully();

                            }

                        }
                    });
        }
    }
}
