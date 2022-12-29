package com.example.a3track.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.a3track.R
import com.google.android.material.snackbar.Snackbar

class SettingsFragment : Fragment() {
    lateinit var logOutButton: Button
    lateinit var viewProfile: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        init(view)

        return view
    }

    private fun init(view: View) {
        logOutButton = view.findViewById(R.id.logOutBtn)
        viewProfile = view.findViewById(R.id.viewProfileTxtView)

        viewProfile.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_profileFragment)
        }

        logOutButton.setOnClickListener {
            // 1.delete token from storage
            // 2.navigate to login page
            // 3.clear backstack
        }
    }
}