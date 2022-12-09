package com.example.a3track.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.a3track.App
import com.example.a3track.R
import com.example.a3track.api.ThreeTrackerRepository
import com.example.a3track.manager.SharedPreferencesManager
import com.example.a3track.viewmodel.LoginViewModel
import com.example.a3track.viewmodel.LoginViewModelFactory

class LoginFragment : Fragment() {
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("TAG", "LoginFragment - onCreate()")
        super.onCreate(savedInstanceState)
        val factory = LoginViewModelFactory(ThreeTrackerRepository())
        loginViewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("TAG", "LoginFragment - onCreateView()")
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val userNameEditText: EditText = view.findViewById(R.id.edittext_name_login_fragment)
        val passwordEditText: EditText = view.findViewById(R.id.edittext_password_login_fragment)
        val button: Button = view.findViewById(R.id.button_login_fragment)

        try {
            Log.d(
                "TAG",
                "token = " + App.sharedPreferences.getStringValue(
                    SharedPreferencesManager.KEY_TOKEN,
                    "Empty token!"
                )
            )
        } catch (e: Exception) {
            Log.d("TAG", e.stackTraceToString())
        }

        button.setOnClickListener {
            val username = userNameEditText.text.toString()
            val password = passwordEditText.text.toString()

            loginViewModel.login(username, password)

            loginViewModel.isSuccessful.observe(this.viewLifecycleOwner) {
                Log.d("TAG", "Logged in successfully = $it")
                if (it) {
                    findNavController().navigate(R.id.action_loginFragment_to_tasksFragment)
                }
            }
        }

        return view
    }
}
