package com.elfstudio.testtask.application;

import android.app.Application;

import com.elfstudio.testtask.api.retro.RetroApi;
import com.elfstudio.testtask.api.retro.RetroClient;

import retrofit2.Retrofit;

public class App extends Application {

    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized App getInstance() {
        return mInstance;
    }

    public Retrofit getRetroClient() {
        return RetroClient.getInstance(getInstance());
    }

    public RetroApi getApi() {
        return getRetroClient().create(RetroApi.class);
    }
}
