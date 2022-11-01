package com.example.android_lab5.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.android_lab5.R

class QuizStartFragment : Fragment() {
    private lateinit var startButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_quiz_start, container, false)
        init(view)
        return view
    }

    private fun init(view: View) {
        startButton = view.findViewById(R.id.startButton)
        startButton.setOnClickListener {
            this.findNavController().navigate(R.id.action_quizStartFragment_to_questionFragment)
        }
    }
}