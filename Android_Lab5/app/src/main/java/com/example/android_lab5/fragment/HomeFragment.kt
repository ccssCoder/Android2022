package com.example.android_lab5.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.android_lab5.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {
    lateinit var startBtn: Button
    lateinit var listBtn: Button
    lateinit var createBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        init(view)
        return view
    }

    private fun init(view: View) {
        startBtn = view.findViewById(R.id.homeToQuizBtn)
        listBtn = view.findViewById(R.id.homeToListBtn)
        createBtn = view.findViewById(R.id.homeToCreateBtn)

        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Should make a ViewModel with LiveData
        // After each button click, should change ViewModel and
        // an observer in MainActivity can manipulate BottomNavigation
        // to use "bottomNavigationView.setSelectedItemId(R.id.quizStartFragment)"
        startBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_quizStartFragment)
        }

        listBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_questionListFragment)
        }

        createBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_questionAddFragment)
        }
    }
}