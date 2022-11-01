package com.example.android_lab5.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.android_lab5.R
import com.example.android_lab5.viewmodel.UserViewModel

class QuizEndFragment : Fragment() {
    private lateinit var scoreTxtV: TextView
    private lateinit var restartBtn: Button
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_quiz_end, container, false)

        init(view)
        return view
    }

    @SuppressLint("SetTextI18n")
    private fun init(view: View) {
        scoreTxtV = view.findViewById(R.id.quizResult)
        scoreTxtV.text = userViewModel.correctAnswers.toString() + "/" + userViewModel.numberOfQuestions.toString()

        restartBtn = view.findViewById(R.id.tryAgain)
        restartBtn.setOnClickListener{
            userViewModel.flushValues()
            this.findNavController().navigate(R.id.action_quizEndFragment_to_quizStartFragment)
        }
    }
}