package com.example.loginmvp.data.network;



import com.example.loginmvp.data.network.model.APIClient;
import com.example.loginmvp.data.network.model.API;
import com.google.gson.JsonObject;
import com.example.loginmvp.data.network.model.Login_Model;
import rx.Observable;


/**
 * Created by user on 10/16/2017.
 */

public class AppApiHelper implements ApiHelper {

    @Override
    public Observable<JsonObject> login(String username, String password) {

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

           return apiService.login(login);
        }

}
