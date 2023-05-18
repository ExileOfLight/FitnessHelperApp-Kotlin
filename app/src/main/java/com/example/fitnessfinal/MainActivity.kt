package com.example.fitnessfinal


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.fitnessfinal.databinding.ActivityMainBinding
import com.example.fitnessfinal.db.UserDataBase
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedViewModel: MainViewModel
    private lateinit var loadingJob: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        sharedViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.viewModel = sharedViewModel
        binding.lifecycleOwner = this

        val view: View = binding.root
        setContentView(view)

        val navController = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.menu_item_calories, R.id.menu_item_food, R.id.menu_item_settings)
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.bottomNavigation.setupWithNavController(navController)

//        val loadingFragment = supportFragmentManager.findFragmentById(R.id.loading_fragment)
//        val loadingLayout = loadingFragment?.view
//        loadingLayout?.visibility = View.VISIBLE
//        binding.bottomNavigation.visibility = View.GONE
//
//        loadingJob = CoroutineScope(Dispatchers.Main).launch {
//            delay(10000) // Set the loading screen duration in milliseconds
//
//        }
//        loadingLayout?.visibility = View.GONE
//        binding.bottomNavigation.visibility = View.VISIBLE

    }
    override fun onDestroy() {
        super.onDestroy()
        if (loadingJob.isActive) {
            loadingJob.cancel()
        }
    }



}