package com.example.a3track

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.a3track.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var toolbar: Toolbar

    private lateinit var toolbarNavigateToAddTaskPageImgV: ImageView
    private lateinit var toolbarAddTaskBtn: Button

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

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.tasksFragment,
                R.id.groupsFragment,
                R.id.settingsFragment,
            )
        )

        setupToolbar()

        setupActionBarWithNavController(navController, appBarConfiguration)

        hideNavBars()

        bottomNavigationView.setupWithNavController(navController)
    }

    private fun setupToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        toolbarNavigateToAddTaskPageImgV = findViewById(R.id.NavigateAddTaskImV)
        toolbarAddTaskBtn = findViewById(R.id.createTaskBtn)

        toolbarNavigateToAddTaskPageImgV.setOnClickListener {
            navController.navigate(R.id.addTaskFragment)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    /**
     This method helps the MainActivity(FragmentHolder) to keep showing
     or hiding specific elements on the tool/navigation-bar
    */
    private fun hideNavBars() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment -> {
                    toolbar.visibility = View.GONE
                    bottomNavigationView.visibility = View.GONE
                    toolbarAddTaskBtn.visibility = View.GONE
                    toolbarNavigateToAddTaskPageImgV.visibility = View.GONE
                }
                R.id.taskDetailFragment -> {
                    toolbar.visibility = View.VISIBLE
                    bottomNavigationView.visibility = View.VISIBLE
                    toolbarAddTaskBtn.visibility = View.GONE
                    toolbarNavigateToAddTaskPageImgV.visibility = View.GONE
                }
                R.id.addTaskFragment -> {
                    toolbar.visibility = View.VISIBLE
                    bottomNavigationView.visibility = View.VISIBLE
                    toolbarAddTaskBtn.visibility = View.VISIBLE
                    toolbarNavigateToAddTaskPageImgV.visibility = View.GONE
                }
                R.id.tasksFragment -> {
                    toolbar.visibility = View.VISIBLE
                    bottomNavigationView.visibility = View.VISIBLE
                    toolbarAddTaskBtn.visibility = View.GONE
                    toolbarNavigateToAddTaskPageImgV.visibility = View.VISIBLE
                }
                else -> {
                    toolbar.visibility = View.VISIBLE
                    bottomNavigationView.visibility = View.VISIBLE
                    toolbarAddTaskBtn.visibility = View.GONE
                    toolbarNavigateToAddTaskPageImgV.visibility = View.GONE
                }
            }
        }
    }
}
