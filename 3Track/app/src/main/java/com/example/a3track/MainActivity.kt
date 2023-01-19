package com.example.a3track

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.a3track.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("TAG", "MainActivity - onCreate()")
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        navController = findNavController(R.id.nav_host_fragment)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.tasksFragment,
                R.id.groupsFragment,
                R.id.settingsFragment,
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        hideNavBars()

        bottomNavigationView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun hideNavBars() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment -> {
                    toolbar.visibility = View.GONE
                    bottomNavigationView.visibility = View.GONE
                }
                R.id.taskDetailFragment -> {
                    bottomNavigationView.visibility = View.GONE
                }
                else -> {
                    toolbar.visibility = View.VISIBLE
                    bottomNavigationView.visibility = View.VISIBLE
                }
            }
        }
    }
}
