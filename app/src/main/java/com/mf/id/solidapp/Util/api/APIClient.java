package com.mf.id.solidapp.Util.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static String url = "http://solidcontentmanagementsystem.com/";

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(url)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create());

    private static ApiInterface client;

    public static ApiInterface getInstance(){
        if(client==null)
            client = builder.build().create(ApiInterface.class);
        return client;
    }
}
