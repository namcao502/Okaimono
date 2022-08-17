package com.example.okaimono

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.okaimono.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var rememberCount: SharedPreferences

    private var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        loadInitialState()

    }

    private fun shakeImage() {
        val shake: Animation = AnimationUtils.loadAnimation(this, R.anim.shake)
        binding.imageViewMiku.startAnimation(shake)
    }

    private fun updateCount(){
        count++
        binding.textViewCount.text = count.toString()
        val editor = rememberCount.edit()
        editor.putInt("count", count)
        editor.apply()
    }

    private fun startMusicInBackground(){
        val intent = Intent(this, BackgroundServices::class.java)
        startService(intent)
    }

    private fun loadInitialState(){
        //load
        rememberCount = getSharedPreferences("remember", MODE_PRIVATE)
        count = rememberCount.getInt("count", 0)
        binding.textViewCount.text = count.toString()

        binding.imageViewMiku.setOnClickListener {
            updateCount()
            shakeImage()
            startMusicInBackground()
        }
    }

}