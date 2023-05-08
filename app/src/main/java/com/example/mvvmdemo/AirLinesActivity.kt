package com.example.mvvmdemo

import android.annotation.SuppressLint
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.JsonReader
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mvvmdemo.adapter.AirLinesAdapter
import com.example.mvvmdemo.api.QuoteApi
import com.example.mvvmdemo.databinding.ActivityAirLinesBinding
import com.example.mvvmdemo.model.AirLinesList
import com.example.mvvmdemo.model.AirLinesListItem
import com.example.mvvmdemo.repository.QuoteRepo
import com.example.mvvmdemo.services.AirLineService
import com.example.mvvmdemo.viewmodel.QuoteModelFactory
import com.example.mvvmdemo.viewmodel.QuoteViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AirLinesActivity : AppCompatActivity() {
    lateinit var airLinesBinding: ActivityAirLinesBinding;

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        airLinesBinding = ActivityAirLinesBinding.inflate(layoutInflater);
        val view = airLinesBinding.root;
        setContentView(view);

        supportActionBar?.title = "Airline App";
        var customBack = resources.getDrawable(R.drawable.custom_actionbar_background, null);
        supportActionBar?.setBackgroundDrawable(customBack);

        //airLinesBinding.airLinesRecyclerView.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL);
        airLinesBinding.airLinesRecyclerView.layoutManager =LinearLayoutManager(this);

        try {
            var airlineservice = AirLineService.getInstance().create(QuoteApi::class.java);

            //using mvvm architecture
            /*
            var quoteRepo = QuoteRepo(airlineservice);

            var airLinesViewModel =
                ViewModelProvider(this, QuoteModelFactory(quoteRepo))[QuoteViewModel::class.java];

            airLinesViewModel.airLinesData.observe(this) {
                if (it == null) {
                    airLinesBinding.airLineProgressBar.visibility = View.VISIBLE;
                } else {
                    airLinesBinding.airLineProgressBar.visibility = View.INVISIBLE;
                    var adapter = AirLinesAdapter(it, applicationContext);
                    airLinesBinding.airLinesRecyclerView.adapter = adapter;
                }
            }

             */

            //using simple call response
            var response: Call<AirLinesList> = airlineservice.airLinesData();


            response.enqueue(object : Callback<AirLinesList> {
                override fun onResponse(
                    call: Call<AirLinesList>, response: Response<AirLinesList>
                ) {
                    if (response.isSuccessful) {
                        airLinesBinding.airLineProgressBar.visibility = View.INVISIBLE;
                        var adapter =
                            response.body()?.let { AirLinesAdapter(it, applicationContext) };
                        airLinesBinding.airLinesRecyclerView.adapter = adapter;
                    }
                }

                override fun onFailure(call: Call<AirLinesList>, t: Throwable) {

                }

            })

        } catch (e: Exception) {
            Toast.makeText(applicationContext, "Something went wrong!!!", Toast.LENGTH_LONG).show();
        }
    }
}