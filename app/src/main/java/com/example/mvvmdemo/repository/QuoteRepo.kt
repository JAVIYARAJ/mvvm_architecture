package com.example.mvvmdemo.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmdemo.api.QuoteApi
import com.example.mvvmdemo.model.AirLinesList
import com.example.mvvmdemo.model.AirLinesListItem
import com.example.mvvmdemo.model.QuoteList
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class QuoteRepo(private var quoteApi: QuoteApi) {

    private val mutableQuote = MutableLiveData<QuoteList>();


    //use for observe quote list
    val quotes: LiveData<QuoteList> get() = mutableQuote;

    suspend fun getQuotes(page: Int) {
        try {
            var result = quoteApi.getQuote(page);
            if (result?.body() != null) {
                mutableQuote.postValue(result.body());
            }
        }catch (e:Exception){
            Log.d("Practice", e.message.toString());

        }

    }

    private var mutableAirLineData = MutableLiveData<AirLinesList>();

    val airlinesLiveData: LiveData<AirLinesList> get() = mutableAirLineData;

    suspend fun getAirLinesData() {
        try {
            var result = quoteApi.getAirLinesData();
            if (result?.body() != null) {
                mutableAirLineData.postValue(result.body());
            }
        }catch (e:Exception){
            Log.d("PracticeRepo", e.message.toString());
        }

    }

}