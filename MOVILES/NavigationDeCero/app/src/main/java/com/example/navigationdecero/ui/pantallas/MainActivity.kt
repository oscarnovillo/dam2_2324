package com.example.navigationdecero.ui.pantallas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import com.example.navigationdecero.R
import com.example.navigationdecero.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_appbar, menu)
        return true
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
//
            setSupportActionBar(topAppBar)
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            navController = navHostFragment.findNavController()
            appBarConfiguration = AppBarConfiguration(setOf(
                R.id.primerFragment, R.id.tercerFragment
            ), drawerLayout)
            setupActionBarWithNavController(navController, appBarConfiguration)
            navView.setupWithNavController(navController)



            topAppBar.setNavigationOnClickListener {
                Log.i("TAG",navController.currentDestination?.id.toString() ?: "null" )

                navController.navigateUp()
                drawerLayout.open()
            }

//            topAppBar.setOnMenuItemClickListener { menuItem ->
//
//            }


            //topAppBar.navigationIcon = getDrawable(R.drawable.ic_baseline_menu_24)

            navController.addOnDestinationChangedListener { _, destination, arguments ->
                topAppBar.isVisible = arguments?.getBoolean("ShowAppBar", true) == true
                //topAppBar.navigationIcon = getDrawable(R.drawable.ic_baseline_menu_24)

                when (destination.id){
                    R.id.segundoFragment -> {
                        topAppBar.visibility = View.VISIBLE
                        topAppBar.menu.findItem(R.id.search).isVisible = true
                    }
                    R.id.tercerFragment -> {
                        topAppBar.visibility = View.VISIBLE
                        topAppBar.menu.findItem(R.id.search).isVisible = false
                    }
                    R.id.primerFragment -> {
                        //topAppBar.navigationIcon = getDrawable(R.drawable.ic_launcher_background)
                    }
                }
            }


            //supportActionBar?.setHomeButtonEnabled(true)
            //topAppBar.visibility = android.view.View.GONE


        }
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.segundoFragment -> {
                // Handle favorite icon press
                menuItem.onNavDestinationSelected(navController)
                true
            }
            R.id.tercerFragment -> {
                // Handle search icon press
                val action = PrimerFragmentDirections.actionPrimerFragmentToTercerFragment("desde primero")
                navController.navigate(action)
                true
            }
            R.id.search -> {
                // Handle search icon press
                true
            }
            R.id.bottom -> {
                Intent(this, MainActivityBottom::class.java).also {
                    startActivity(it)
                }
                true
            }
            else -> false
        }
    }
}