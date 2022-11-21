package com.example.android_lab5.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.android_lab5.R
import com.example.android_lab5.viewmodel.ItemViewModel
import com.example.android_lab5.viewmodel.UserViewModel

class QuestionDetailFragment : Fragment() {
    lateinit var questionTxtView: TextView
    lateinit var answer1TxtView: TextView
    lateinit var answer2TxtView: TextView
    lateinit var answer3TxtView: TextView
    lateinit var answer4TxtView: TextView
    val itemViewModel: ItemViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_question_detail, container, false)

        init(view)

        return view
    }

    private fun init(view: View) {
        questionTxtView = view.findViewById(R.id.questionTxtView)
        answer1TxtView = view.findViewById(R.id.answerTxtView1)
        answer2TxtView = view.findViewById(R.id.answerTxtView2)
        answer3TxtView = view.findViewById(R.id.answerTxtView3)
        answer4TxtView = view.findViewById(R.id.answerTxtView4)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val item = itemViewModel.item

        questionTxtView.text = item.question
        answer1TxtView.text = item.answers[0]
        answer2TxtView.text = item.answers[1]
        answer3TxtView.text = item.answers[2]
        answer4TxtView.text = item.answers[3]
    }
}