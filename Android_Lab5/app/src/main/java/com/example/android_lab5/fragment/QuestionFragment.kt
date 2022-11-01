package com.example.android_lab5.fragment

import com.example.android_lab5.controller.ItemController
import ItemRepository
import ItemService
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android_lab5.R

class QuestionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_question, container, false)
        init(view)
        return view
    }

    private fun init(view: View) {
        val itemController = ItemController(
            ItemService(ItemRepository()),
            requireActivity(),
            view,
            view.findViewById(R.id.questionText),
            view.findViewById(R.id.radioGroup),
            view.findViewById(R.id.radioButton1),
            view.findViewById(R.id.radioButton2),
            view.findViewById(R.id.radioButton3),
            view.findViewById(R.id.radioButton4),
            view.findViewById(R.id.next),
        )

        itemController.quiz(2)
    }
}

