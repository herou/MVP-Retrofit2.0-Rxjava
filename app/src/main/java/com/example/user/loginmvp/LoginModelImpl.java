package com.example.user.loginmvp;

import android.util.Log;


import com.example.user.loginmvp.rest.API;
import com.example.user.loginmvp.rest.APIClient;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by user on 10/16/2017.
 */

public class LoginModelImpl implements LoginModel{

    @Override
    public void login(String username, String password, final OnLoginFinishedListener onLoginFinishedListener) {

        Login_Model login;

            final API apiService =
                    APIClient.createAPI_LOGIN_PAGES().create(API.class);

            login  = new Login_Model();

            login.setAction("");
            login.setUsername("");
            login.setPassword("");

            login.setAction("login_utente");
            login.setUsername(username);
            login.setPassword(password);

            apiService.login(login)
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
                        onLoginFinishedListener.onResponse(response);
                    }
                });

        }

}
