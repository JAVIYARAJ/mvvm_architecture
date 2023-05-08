package com.example.mvvmdemo.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AirLineService {

    private const val BASE_URL_AIRLINES = "https://api.instantwebtools.net/v1/";


    fun getInstance(): Retrofit {
    var interceptor=HttpLoggingInterceptor();
        interceptor.level=HttpLoggingInterceptor.Level.BODY;

        var client=OkHttpClient.Builder().addInterceptor(interceptor).build();

        return Retrofit.Builder().client(client).baseUrl(BASE_URL_AIRLINES)
            .addConverterFactory(GsonConverterFactory.create()).build();
    }
}