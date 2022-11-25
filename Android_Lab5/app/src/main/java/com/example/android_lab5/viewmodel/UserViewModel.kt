package com.example.android_lab5.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel() {
    var correctAnswers = 0
    var numberOfQuestions = 0
    var highScore = MutableLiveData<Int>()
    var name = MutableLiveData<String>()

    fun increaseCorrectAnswers(){ correctAnswers++ }

    fun flushValues(){
        correctAnswers = 0
        numberOfQuestions = 0
    }

    fun setHighScore() {
        try {
//            if (correctAnswers > highScore.value!!) {
//                highScore.value = correctAnswers
//            }
        } catch (e: Exception) {
            Log.d("XXX", "${e.stackTrace}")
        }
    }
}