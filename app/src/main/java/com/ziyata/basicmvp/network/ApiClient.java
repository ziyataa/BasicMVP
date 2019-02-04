package com.ziyata.basicmvp.network;

import com.ziyata.basicmvp.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;

    // Membuat method return untuk mendapatkan retrofit yang sudah berisi baseUrl
    public static Retrofit getCliend() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
