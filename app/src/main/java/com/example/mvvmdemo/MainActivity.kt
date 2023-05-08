package com.example.mvvmdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdemo.adapter.QuoteAdapter
import com.example.mvvmdemo.api.QuoteApi
import com.example.mvvmdemo.databinding.ActivityMainBinding
import com.example.mvvmdemo.repository.QuoteRepo
import com.example.mvvmdemo.services.QuoteService
import com.example.mvvmdemo.viewmodel.QuoteModelFactory
import com.example.mvvmdemo.viewmodel.QuoteViewModel

class MainActivity : AppCompatActivity() {

    lateinit var mainActivityBinding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater);

        val view = mainActivityBinding.root;
        setContentView(view);

        supportActionBar?.title = "Quote App";
        var customBack = resources.getDrawable(R.drawable.custom_actionbar_background, null);
        supportActionBar?.setBackgroundDrawable(customBack);

        mainActivityBinding.quoteRecyclerView.layoutManager = LinearLayoutManager(this);

        try {
            var quoteService = QuoteService.getInstance().create(QuoteApi::class.java);

            var quoteRepo = QuoteRepo(quoteService);

            var quoteViewModel =
                ViewModelProvider(this, QuoteModelFactory(quoteRepo))[QuoteViewModel::class.java];

            quoteViewModel.quotes.observe(this) {
                var adapter = QuoteAdapter(it.results, applicationContext);
                mainActivityBinding.quoteRecyclerView.adapter = adapter;
            }

        } catch (e: Exception) {
            Toast.makeText(applicationContext, "Something went wrong!!!", Toast.LENGTH_LONG).show();
        }

    }
}