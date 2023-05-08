package com.example.mvvmdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmdemo.repository.QuoteRepo

class QuoteModelFactory(private var quoteRepo: QuoteRepo) :
    ViewModelProvider.Factory {
    //use when we need to use extra parameter for view model
    //this method generate view model instance.

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return QuoteViewModel(quoteRepo) as T;
    }
}