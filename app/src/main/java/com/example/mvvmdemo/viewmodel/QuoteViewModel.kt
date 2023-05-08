package com.example.mvvmdemo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmdemo.model.AirLinesList
import com.example.mvvmdemo.model.AirLinesListItem
import com.example.mvvmdemo.model.QuoteList
import com.example.mvvmdemo.repository.QuoteRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

class QuoteViewModel(private var quoteRepo: QuoteRepo, private var page: Int = 1) : ViewModel() {

    //get all quotes from api
    init {
        //here we create a top level coroutines that work on whole application lifetime (not cancelled prematurely.)
        //so here launch function create new coroutines without blocking  main or current thread and return as job (job object use to perform multiple operation on coroutines like cancel,isActive,isCompleted,start,join)
        try {
            var job = GlobalScope.launch(Dispatchers.Main) {
                quoteRepo.getQuotes(page)
                quoteRepo.getAirLinesData();
            }

        } catch (e: Exception) {
            Log.d("PracticeViewModel", e.message.toString());
        }

    }

    //use for observe data
    val quotes: LiveData<QuoteList> get() = quoteRepo.quotes;

    val airLinesData: LiveData<AirLinesList> get() = quoteRepo.airlinesLiveData;

}