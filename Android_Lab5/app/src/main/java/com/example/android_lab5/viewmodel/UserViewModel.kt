package com.example.android_lab5.viewmodel

import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel() {
    var correctAnswers = 0
    var numberOfQuestions = 0

    fun increaseCorrectAnswers(){ correctAnswers++ }

    fun flushValues(){
        correctAnswers = 0
        numberOfQuestions = 0
    }
}