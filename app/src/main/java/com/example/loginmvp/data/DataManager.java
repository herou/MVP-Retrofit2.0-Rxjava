package com.example.loginmvp.data;

import android.content.Context;

import com.example.loginmvp.data.network.ApiHelper;
import com.example.loginmvp.data.network.AppApiHelper;
import com.google.gson.JsonObject;

import rx.Observable;

public class DataManager implements ApiHelper{

    ApiHelper apiHelper;
    Context context;

    public DataManager(Context context) {
        this.apiHelper = new AppApiHelper();
        this.context = context;
    }

    @Override
    public Observable<JsonObject> login(String username, String password) {
        return apiHelper.login(username,password);
    }
}
