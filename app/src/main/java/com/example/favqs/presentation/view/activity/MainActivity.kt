package com.example.favqs.presentation.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import com.example.favqs.R
import com.example.favqs.data.local.Quotes
import com.example.favqs.databinding.ActivityMainBinding
import com.example.favqs.util.API_KEY


class MainActivity : AppCompatActivity() {
    lateinit var topAnimation : Animation
    lateinit var bottomAnimation : Animation
    lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.hide();
        setContentView(binding.root)
        setupDisplayAnimation()
        updateStatusBarColor(R.color.splash_bg)
    }




    override fun onResume() {
        super.onResume()
        activateDelayForAnimation()
    }

    private fun setupDisplayAnimation(){
        /**
         * Declaring the Animations from res file
         */
        topAnimation = AnimationUtils.loadAnimation(this,R.anim.upward_animation)
        bottomAnimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation)

        /**
         * Assigning the animations to the respective views
         */
        with(binding){
            upRandomQuote.animation = topAnimation
            upRandomQuote.text = Quotes.listOfQuotes.shuffled()[0]

            downRandomQuote.animation = bottomAnimation
            downRandomQuote.text = Quotes.listOfQuotes.shuffled()[1]

        }

    }


    private fun activateDelayForAnimation(){
        val handler = Handler(mainLooper)
        handler.postDelayed({
            val intent = Intent(this,DashboardActivity::class.java)
            startActivity(intent)
            finish()
        },3000)
    }

    private fun updateStatusBarColor(color : Int){
        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.statusBarColor = ContextCompat.getColor(this, color)

    }
}