package com.example.mvvmdemo.api

import com.example.mvvmdemo.model.AirLinesList
import com.example.mvvmdemo.model.AirLinesListItem
import com.example.mvvmdemo.model.QuoteList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface QuoteApi {


    //defined api end points
    @GET("/quotes")
    suspend fun getQuote(@Query("page") page: Int): Response<QuoteList>

    @GET("airlines")
    suspend fun getAirLinesData(): Response<AirLinesList>

    @GET("airlines")
    fun airLinesData(): Call<AirLinesList>
}