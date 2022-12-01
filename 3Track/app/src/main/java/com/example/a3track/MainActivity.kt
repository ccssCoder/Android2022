package com.example.a3track

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.a3track.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("TAG", "MainActivity - onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
    }
}
