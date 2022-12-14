package com.example.android_lab5.controller

import Item
import ItemService
import android.app.AlertDialog
import android.content.DialogInterface
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.example.android_lab5.R
import com.example.android_lab5.viewmodel.UserViewModel
import com.google.android.material.snackbar.Snackbar

class ItemController(
    private val itemService: ItemService,
    private val context: FragmentActivity,
    private val view: View,
    private var questionTxtV: TextView,
    private var radioGroup: RadioGroup,
    private var answerRBtn1: RadioButton,
    private var answerRBtn2: RadioButton,
    private var answerRBtn3: RadioButton,
    private var answerRBtn4: RadioButton,
    private var nextBtn: Button,
) {
    private var itemListIndex: Int = 0
    private lateinit var itemList: List<Item>
    private val model: UserViewModel by context.viewModels()
    private val  dialogBuilder = AlertDialog.Builder(context)

    init {
        dialogBuilder.setMessage("Are you sure you want to end this quiz?")
            // if the dialog is cancelable
            .setCancelable(false)
            .setNegativeButton("No", DialogInterface.OnClickListener {
                    dialog, _ ->
                dialog.dismiss()
            })
            .setPositiveButton("Yes", DialogInterface.OnClickListener {
                    _, _ ->
                context.finish();
            })
    }

    fun quiz(nr: Int) {
        exitQuizByBackPressHandler()
        nextBtn.setOnClickListener {
            try {
                evaluateAnswer(view)
                nextFragmentOrNextQuestionLogic()
            } catch (e: Exception){
                Snackbar.make( view, "Select an answer!", 800).show()
            }
        }

        itemList = itemService.selectRandomItems(nr)

        model.numberOfQuestions = itemList.size
        setUpQuestionPropertiesOnView()
    }

    private fun evaluateAnswer(view: View) {
        val selectedOption: Int = radioGroup.checkedRadioButtonId
        val radioButton = view.findViewById<RadioButton>(selectedOption)
        if(selectedOption == -1){
            throw Exception()
        }

        val item: Item = itemList[itemListIndex-1]
        if(radioButton.text.toString() == item.answers[ item.correct ]){
            model.increaseCorrectAnswers()
        }
    }

    private fun nextFragmentOrNextQuestionLogic() {
        if (itemListIndex < itemList.size)
            setUpQuestionPropertiesOnView()
        else {
            view.findNavController().navigate(R.id.action_questionFragment_to_quizEndFragment)
        }
    }

    private fun setUpQuestionPropertiesOnView() {
        questionTxtV.text = itemList[itemListIndex].question

        answerRBtn1.text = itemList[itemListIndex].answers[0]
        answerRBtn2.text = itemList[itemListIndex].answers[1]
        answerRBtn3.text = itemList[itemListIndex].answers[2]
        answerRBtn4.text = itemList[itemListIndex].answers[3]

        itemListIndex++
    }
    
    private fun exitQuizByBackPressHandler(){
        context.onBackPressedDispatcher.addCallback(
            context,
            object: OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    val alert = dialogBuilder.create()
                    alert.setTitle("Exit")
                    alert.show()
                }
            }
        )
    }
}