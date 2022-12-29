package com.example.a3track.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.a3track.R
import com.example.a3track.api.ThreeTrackerRepository
import com.example.a3track.viewmodel.ProfileViewModel
import com.example.a3track.viewmodel.ProfileViewModelFactory
import com.example.a3track.viewmodel.TasksViewModel
import com.example.a3track.viewmodel.TasksViewModelFactory

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    lateinit var profileImage: ImageView
    lateinit var name: TextView
    lateinit var email: TextView
    lateinit var phone: TextView
    lateinit var location: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("XXX", "ProfileFragment - OnCreate()")
        super.onCreate(savedInstanceState)
        val factory = ProfileViewModelFactory(ThreeTrackerRepository())
        profileViewModel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        Log.d("XXX", "ProfileFragment - OnCreateView()")
        init(view)

        return view
    }

    private fun init(view: View) {
        profileImage = view.findViewById(R.id.profileImageView)
        name = view.findViewById(R.id.nameTxtView)
        email = view.findViewById(R.id.emailTxtView)
        phone = view.findViewById(R.id.phoneTxtView)
        location = view.findViewById(R.id.locationTxtView)

        profileViewModel.profile.observe(viewLifecycleOwner) {
            Log.d("TAG", "User object: $it")
            name.text = profileViewModel.profile.value?.firstName + " " + profileViewModel.profile.value?.lastName

            email.text = profileViewModel.profile.value?.email
            phone.text = profileViewModel.profile.value?.phoneNumber
            location.text = profileViewModel.profile.value?.location

            if(profileViewModel.profile.value?.profilePictureURL != null) {
                Glide.with(this)
                    .load(profileViewModel.profile.value?.profilePictureURL)
                    .override(120, 120)
                    .transform(RoundedCorners(60))
                    .into(profileImage)
            }
        }
    }
}