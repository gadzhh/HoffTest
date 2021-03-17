package com.example.hofftest;

import android.app.Application;

import com.example.hofftest.data.DataManager;
import com.example.hofftest.data.ProductApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static final String BASE_URL = "https://hoff.ru/api/v2/";
    private static Retrofit retrofit;
    private static ProductApi api;
    private static DataManager dataManager;

    public static ProductApi getJSONApi() {
        return api;
    }

    public static DataManager getDataManager() {
        return dataManager;
    }

    @Override
    public void onCreate() {
        super.onCreate();


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        api = retrofit.create(ProductApi.class);
        dataManager = new DataManager();
    }
}
