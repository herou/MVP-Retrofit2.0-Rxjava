package com.example.loginmvp.data.network.model;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.loginmvp.data.network.model.API.BASE_URL;
import static com.example.loginmvp.data.network.model.API.BASE_URL_LOGIN_PAGES;

/**
 * Created by Eraldi on 12/16/2016.
 */

public class APIClient {


    //public static final String BASE_URL = "http://develop.almotech.org/";
    private static Retrofit retrofit = null;


    public static Retrofit createAPI() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit createAPI_LOGIN_PAGES() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL_LOGIN_PAGES)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


    //////////////////// Methot Get Without Headers///////////////////

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


    //////////////////// Methot Get With Headers///////////////////

    public static API getApi(final String Token) {
        //Log.d("RETROADAPTER", "APIRETro: " + Token);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        Request request = original.newBuilder()
                                .header("Content-Type", "application/json; charset=utf-8")
                                .header("Authorization", Token)
                                .method(original.method(), original.body())
                                .build();

                        return chain.proceed(request);
                    }
                })
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                //.addInterceptor(logging)
                .build();



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit.create(API.class);
    }


    public static API createAPI_Token(final String Token) {



        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        Request request = original.newBuilder()
                                .header("Content-Type", "application/json; charset=utf-8")
                                .header("Authorization", Token)
                                .method(original.method(), original.body())
                                .build();

                        return chain.proceed(request);
                    }
                })
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                //.addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                     .client(okHttpClient)
                    .build();
        return retrofit.create(API.class);
        }

    }





