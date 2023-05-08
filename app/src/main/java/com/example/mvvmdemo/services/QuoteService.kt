package com.example.mvvmdemo.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//singleton object
object QuoteService {
    private const val BASE_URL = "https://quotable.io/";

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build();
    }

}