package com.example.user.loginmvp;

import android.widget.Toast;

import com.example.user.loginmvp.rest.API;
import com.example.user.loginmvp.rest.APIClient;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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


            Call<JsonObject> userCallbackCall = apiService.login(login);
            userCallbackCall.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                    try {
                        JSONObject jsonObject = new JSONObject(response.body().toString());
                        String status = jsonObject.getString("status");
                        if(!status.equalsIgnoreCase("error")){
                            onLoginFinishedListener.onSuccessfully();
                        }else{
                            onLoginFinishedListener.checkUsernamePassword();
                        }

                    } catch (JSONException e) {
                        onLoginFinishedListener.onError();
                        e.printStackTrace();
                    }

                }
                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    onLoginFinishedListener.onError();
                }
            });
        }

}
