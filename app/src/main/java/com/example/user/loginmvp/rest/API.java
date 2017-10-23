package com.example.user.loginmvp.rest;

import com.example.user.loginmvp.Login_Model;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Aron on 5/21/2016.
 */
public interface API {
    //String BASE_URL = "http://46.101.125.115/news/index.php/"; //replace this with your base url
    // String BASE_URL = "http://192.168.43.77/backends/paper/index.php/api/"; //replace this with your base url
    String BASE_URL = "http://chatstreet.it/"; //replace this with your base url
    String BASE_URL_LOGIN_PAGES= "http://www.tuttiannunci.it/pages/";

    @Headers("Content-Type: application/json")
    @POST("api.php")
    Observable<JsonObject> login(@Body Login_Model req);




}
