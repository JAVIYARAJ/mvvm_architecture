package com.example.mvvmdemo

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.example.mvvmdemo.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var homeBinding: ActivityHomeBinding;
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        homeBinding=ActivityHomeBinding.inflate(layoutInflater);
        val view=homeBinding.root;
        setContentView(view);

        supportActionBar?.title = "Home Screen";
        var customBack = resources.getDrawable(R.drawable.custom_actionbar_background, null);
        supportActionBar?.setBackgroundDrawable(customBack);

        homeBinding.quoteApp.setOnClickListener {
            var intent=Intent(this,MainActivity::class.java);
            startActivity(intent)
        }

        homeBinding.airlineApp.setOnClickListener {
            var intent=Intent(this,AirLinesActivity::class.java);
            startActivity(intent)
        }

    }
}