package com.example.flows.framework.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flows.R
import com.example.flows.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var movieAdapter: MovieAdapter

    private val viewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { value ->
//                    when (value) {
//                        is UiState.Failure -> {
//                            Toast.makeText(this@MainActivity, value.mensaje, Toast.LENGTH_SHORT)
//                                .show()
//                            binding.loading.visibility = View.GONE
//                        }
                    binding.loading.visibility = if (value.isLoading) View.VISIBLE else View.GONE
                    movieAdapter.submitList(value.movies)
                    value.error.let {
                        Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
                        viewModel.handleEvent(MainContract.Event.MensajeMostrado)
                    }
//                    }
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiError.collect {
                    Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun init() {

        val layoutManager = LinearLayoutManager(this)
        binding.rvMovies.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(
            binding.rvMovies.context,
            layoutManager.orientation
        )
        binding.rvMovies.addItemDecoration(dividerItemDecoration)

        movieAdapter = MovieAdapter()
        binding.rvMovies.adapter = movieAdapter
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }


    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.recargar -> {
                // Handle favorite icon press

                viewModel.handleEvent(MainContract.Event.PedirDatos)
                true
            }
            R.id.other -> {
                //menuItem.onNavDestinationSelected(navController)
                // Handle search icon press
                //findNavController().navigate(R.id.action_blankFragment2_to_blankFragment3)
                true
            }
            else -> super.onOptionsItemSelected(menuItem)
        }
        // return super.onOptionsItemSelected(menuItem)
    }


}