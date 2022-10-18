package com.example.android_lab4

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class SecondActivity : AppCompatActivity() {
    lateinit var receiveText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        receiveText = findViewById(R.id.receivedName)

        // create the get Intent object
        val intent = intent
        // receive the value by getStringExtra() method and
        // key must be same which is send by first activity
        val str = intent.getStringExtra("message_key")
        // display the string into textView
        receiveText.text = str
    }
}