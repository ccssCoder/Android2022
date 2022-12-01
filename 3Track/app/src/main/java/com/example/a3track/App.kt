package com.example.a3track

import android.app.Application
import android.util.Log
import com.example.a3track.manager.SharedPreferencesManager

class App: Application() {
    companion object {
        lateinit var sharedPreferences: SharedPreferencesManager
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("TAG", "App: Application() - SharedPreferencesManager(applicationContext)")
        sharedPreferences = SharedPreferencesManager(applicationContext)
    }
}
