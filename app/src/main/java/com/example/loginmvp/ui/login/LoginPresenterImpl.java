package com.example.loginmvp.ui.login;


import android.content.Context;
import android.util.Log;

import com.example.loginmvp.data.DataManager;
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
    Context context;
    DataManager dataManager;

    public LoginPresenterImpl(Login login, Context context) {
        this.loginView = login;
        this.context = context;
        dataManager = new DataManager(context);
    }
    @Override
    public void validateCred(String username, String password) {
        if(username.equalsIgnoreCase("") || password.equalsIgnoreCase("")){
            loginView.showCheckFields();
        }else {
            loginView.showProgressDialog();

            dataManager.login(username, password)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<JsonObject>() {
                        @Override
                        public void onCompleted() {
                            //
                            Log.d("","");
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
