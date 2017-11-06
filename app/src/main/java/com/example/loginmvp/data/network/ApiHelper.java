package com.example.loginmvp.data.network;

import com.google.gson.JsonObject;

import rx.Observable;

/**
 * Created by user on 10/16/2017.
 */

public interface ApiHelper {

    Observable<JsonObject> login(String username, String password);
}
