package com.example.appnobasica.ui.pantallaMain

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel

import com.example.appnobasica.databinding.ActivityMainBinding
import com.example.appnobasica.databinding.TucasaEslamiaBinding
import com.example.appnobasica.domain.modelo.Persona
import com.example.appnobasica.domain.usecases.personas.AddPersonaUseCase
import com.example.appnobasica.domain.usecases.personas.GetPersonas
import com.example.appnobasica.utils.StringProvider


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            StringProvider.instance(this),
            AddPersonaUseCase(),
            GetPersonas(),
        )
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }


        eventos()
        observarViewModel()
    }

    private fun observarViewModel() {
        viewModel.uiState.observe(this@MainActivity) { state ->

            state.error?.let { error ->
                Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
                viewModel.errorMostrado()
            }
            if (state.error == null)
                binding.editTextTextPersonName.setText(state.persona?.nombre)
        }
    }

    private fun eventos() {

        with(binding) {
            button.setOnClickListener {
                viewModel.addPersona(Persona(editTextTextPersonName.text.toString()))

            }

        }
    }

}