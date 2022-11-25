package com.example.android_lab5.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.android_lab5.R
import com.example.android_lab5.viewmodel.ItemViewModel
import com.example.android_lab5.viewmodel.UserViewModel

class QuizStartFragment : Fragment() {
    private lateinit var startButton: Button
    private lateinit var nameEditTxt: EditText
    val userViewModel: UserViewModel by activityViewModels()

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
        nameEditTxt = view.findViewById(R.id.sendName)

        userViewModel.name.observe(this.viewLifecycleOwner, Observer {
            nameEditTxt.setText(it)
            Log.d("XXX", "LiveData changed to : $it")
        })

        startButton.setOnClickListener {
            userViewModel.name.value = nameEditTxt.text.toString()
            this.findNavController().navigate(R.id.action_quizStartFragment_to_questionFragment)
        }
    }
}