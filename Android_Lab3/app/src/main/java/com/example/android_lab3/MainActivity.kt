package com.example.android_lab3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buttonClickEvent(view: View) {
        Log.i("ButtonClick","CLICK!!!")
        Snackbar.make(
            view,
            "Button Clicked!",
            800,
        ).show()
    }
}