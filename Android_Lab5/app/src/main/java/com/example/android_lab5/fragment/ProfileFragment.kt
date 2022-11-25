package com.example.android_lab5.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.android_lab5.R
import com.example.android_lab5.viewmodel.UserViewModel

class ProfileFragment : Fragment() {
    lateinit var nameEditTxt: EditText
    lateinit var playerScoreTxtView: TextView
    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        init(view)

        return view
    }

    private fun init(view: View) {
        nameEditTxt = view.findViewById(R.id.playerName)
        playerScoreTxtView = view.findViewById(R.id.playerScore)

        userViewModel.name.observe(this.viewLifecycleOwner, Observer {
            nameEditTxt.setText(it)
            Log.d("XXX", "LiveData [name] changed to : $it")
        })

        userViewModel.highScore.observe(this.viewLifecycleOwner, Observer {
            playerScoreTxtView.text = it.toString()
            Log.d("XXX", "LiveData [highScore] changed to : $it")
        })
    }

    override fun onStop() {
        super.onStop()
        userViewModel.name.value = nameEditTxt.text.toString()
    }
}